import java.util.ArrayList;
import java.util.List;

public class WeeklyCheckInSettlement {
    public static class GymMember {
        private static int membershipCounter = 2000;
        private static int membersEnrolled = 0;

        public final String membershipNumber;

        private final int monthlyFee;
        private int feesPaid;
        private final List<String> paymentModes = new ArrayList<>();

        public GymMember(int monthlyFee) {
            membershipCounter++;
            membersEnrolled++;

            this.membershipNumber = "GYM-" + membershipCounter;
            this.monthlyFee = monthlyFee;
            this.feesPaid = 0;
        }

        public int getMonthlyFee() {
            return monthlyFee;
        }

        public void payFee(int amount) {
            feesPaid += amount;
        }

        public void payFee(int amount, String mode) {
            paymentModes.add(mode);
            payFee(amount);
        }

        public int getFeesPaid() {
            return feesPaid;
        }

        public String[] getPaymentModes() {
            return paymentModes.toArray(String[]::new);
        }

        public static boolean isValidReferralCode(String code) {
            if (code == null || code.length() != 4) {
                return false;
            }

            return code.charAt(0) == 'G'
                    && Character.isDigit(code.charAt(1))
                    && Character.isDigit(code.charAt(2))
                    && Character.isUpperCase(code.charAt(3));
        }

        public static int getMembersEnrolled() {
            return membersEnrolled;
        }

        public static String processWeeklyCheckIn(GymMember[] members) {
            int processed = 0;
            int nullSkipped = 0;
            int groupCount = 0;
            int individualCount = 0;

            if (members == null) {
                return "0 processed | 0 null skipped | 0 group | 0 individual";
            }

            for (GymMember member : members) {
                if (member == null) {
                    nullSkipped++;
                } else {
                    processed++;

                    if (member instanceof GroupClassMember) {
                        groupCount++;
                    } else {
                        individualCount++;
                    }
                }
            }

            return processed + " processed | " + nullSkipped
                    + " null skipped | " + groupCount
                    + " group | " + individualCount + " individual";
        }
    }

    public static class GroupClassMember extends GymMember {
        private final String className;

        public GroupClassMember(int monthlyFee, String className) {
            super(monthlyFee);
            this.className = className;
        }

        public String getClassName() {
            return className;
        }
    }

    public static void main(String[] args) {
        GymMember m1 = new GymMember(1000);

        System.out.println(m1.membershipNumber);
        System.out.println(GymMember.getMembersEnrolled());

        System.out.println(GymMember.isValidReferralCode("G45B"));
        System.out.println(GymMember.isValidReferralCode("G4B"));
        System.out.println(GymMember.isValidReferralCode("X45B"));

        m1.payFee(500);
        m1.payFee(500, "UPI");

        System.out.println(m1.getFeesPaid());
        System.out.println(String.join(", ", m1.getPaymentModes()));

        GymMember[] members = {
            new GroupClassMember(1500, "Zumba"),
            null,
            new GymMember(1000)
        };

        System.out.println(GymMember.processWeeklyCheckIn(members));
    }
}