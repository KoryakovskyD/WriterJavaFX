package ru.avalon.javapp.devj140.writerjavafx.authorization;

public class UserController {
    public static boolean checkData(String name, String pass, String repPass) throws UserException {
        if (name.isEmpty() || pass.isEmpty() || repPass.isEmpty()) throw new UserException("Name and password can't be null");
        if (!pass.equals(repPass))
            throw new UserException("Passwords doesn't same");
        String regex = "A-Za-z0-9!";
        if (pass.replaceAll("[" + regex + "]", "").length() > 0)
            throw new UserException("Password can contain characters: " + regex);
        User user = new User(name, pass);
        return true;
    }

    public static String logIn() {
        String id = "12345id";
        // ....
        return id;
    }
}
