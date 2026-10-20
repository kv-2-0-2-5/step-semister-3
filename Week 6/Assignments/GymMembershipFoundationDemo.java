public class GymMembershipFoundationDemo {
    public static class GymMember {
        private final String memberId;
        private final int monthlyFee;
        private int sessionsAttended;

        public GymMember(String memberId, int monthlyFee) {
            if (memberId == null || memberId.trim().length() < 4) {
                throw new IllegalArgumentException("Invalid member ID");
            }

            this.memberId = memberId;
            this.monthlyFee = monthlyFee;
            this.sessionsAttended = 0;
        }

        public String getMemberId() {
            return memberId;
        }

        public int getMonthlyFee() {
            return monthlyFee;
        }

        public void attendSession() {
            sessionsAttended++;
        }

        public int getSessionsAttended() {
            return sessionsAttended;
        }

        public static String signUpBatch(String[] memberIds, int monthlyFee) {
            int signedUp = 0;
            int rejected = 0;

            for (String memberId : memberIds) {
                try {
                    GymMember member = new GymMember(memberId, monthlyFee);

                    if (member.getMemberId() != null) {
                        signedUp++;
                    }
                } catch (IllegalArgumentException e) {
                    rejected++;
                }
            }

            return "Signed Up: " + signedUp + " | Rejected: " + rejected;
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
    }

    public static void main(String[] args) {
        try {
            GymMember member = new GymMember("GM1", 1000);
            System.out.println(member.getMemberId());
        } catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }

        PremiumMember p = new PremiumMember("MEM01", 2000, "Coach Riya");
        p.attendSession();
        p.attendSession();

        System.out.println(p.getSessionsAttended());

        String[] memberIds = {"MEM1", "GM1", "MEM2", " ", "MEM3"};

        System.out.println(GymMember.signUpBatch(memberIds, 1000));
    }
}