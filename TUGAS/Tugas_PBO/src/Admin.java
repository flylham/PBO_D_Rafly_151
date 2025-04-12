class Admin {
    private String username = "Admin151";
    private String password = "password151";

    public boolean login(String inputUsername, String inputPassword) {
        return username.equals(inputUsername) && password.equals(inputPassword);
    }
}