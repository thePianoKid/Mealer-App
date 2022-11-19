package ca.uottawa.mealerapp.userclasses;

public class UsernameConversion {
    public static String encode (String username) {
        return swapAfterAt(username, ".", "_");
    }

    public static String decode (String username) {
        return swapAfterAt(username, "_", ".");
    }

    private static String swapAfterAt(String username, String oldChar, String newChar) {
        // The username is always an email
        String[] domain = username.split("@");
        try {
            return domain[0] + "@" + domain[1].replace(oldChar, newChar);
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalStateException("Username should be in email format.");
        }
    }
}
