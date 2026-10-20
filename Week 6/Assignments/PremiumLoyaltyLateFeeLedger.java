import java.util.Arrays;

public class PremiumLoyaltyLateFeeLedger {
    public static class GymMember {
        private final String memberId;
        private final int monthlyFee;
        private final int[] lateFeeHistory = new int[10];
        private int lateFeeCount;

        public GymMember(String memberId, int monthlyFee) {
            if (memberId == null || memberId.trim().length() < 4) {
                throw new IllegalArgumentException("Invalid member ID");
            }

            this.memberId = memberId;
            this.monthlyFee = monthlyFee;
            this.lateFeeCount = 0;
        }

        public String getMemberId() {
            return memberId;
        }

        public int getMonthlyFee() {
            return monthlyFee;
        }

        protected void chargeLateFee(int amount) {
            if (lateFeeCount < lateFeeHistory.length) {
                lateFeeHistory[lateFeeCount] = amount;
                lateFeeCount++;
            }
        }

        public int[] getLateFeeHistory() {
            return Arrays.copyOf(lateFeeHistory, lateFeeCount);
        }

        public int getTotalLateFees() {
            int total = 0;

            for (int i = 0; i < lateFeeCount; i++) {
                total += lateFeeHistory[i];
            }

            return total;
        }
    }

    public static class PremiumMember extends GymMember {
        private final String trainerName;

        public PremiumMember(String memberId, int monthlyFee, String trainerName) {
            super(memberId, monthlyFee);
            this.trainerName = trainerName;
        }

        public String getTrainerName() {
            return trainerName;
        }

        @Override
        protected void chargeLateFee(int amount) {
            super.chargeLateFee(amount / 2);
        }
    }

    public static void main(String[] args) {
        PremiumMember p = new PremiumMember("MEM5", 2000, "Coach Riya");

        p.chargeLateFee(200);

        System.out.println(p.getTotalLateFees());

        int[] history = p.getLateFeeHistory();
        history[0] = 999;

        System.out.println("Modified copy: " + Arrays.toString(history));
        System.out.println("Stored history: " + Arrays.toString(p.getLateFeeHistory()));
    }
}