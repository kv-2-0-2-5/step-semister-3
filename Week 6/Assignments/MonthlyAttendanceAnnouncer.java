public class MonthlyAttendanceAnnouncer {
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

        public String displayInfo() {
            return "Standard | Sessions: " + sessionsAttended;
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
        public String displayInfo() {
            return "Premium | Trainer: " + trainerName
                    + " | Sessions: " + getSessionsAttended();
        }
    }

    public static String batchPrint(GymMember[] members) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < members.length; i++) {
            if (i > 0) {
                result.append(" | ");
            }

            result.append(members[i].displayInfo());

            if (members[i] instanceof PremiumMember premium) {
                result.append(" [Trainer via downcast: ")
                      .append(premium.getTrainerName())
                      .append("]");
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        GymMember standard = new GymMember("MEM6", 1000);
        GymMember premium = new PremiumMember("MEM7", 2000, "Coach Riya");

        GymMember[] members = {standard, premium};

        System.out.println(batchPrint(members));
    }
}