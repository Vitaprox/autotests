package partTwo.restLesson;

public class User {
    private String login;
    private String password;
    private String email;

    public static class Builder {
        private User newUser;

        public Builder() {
            newUser = new User();
        }

        public Builder withLogin(String login) {
            newUser.login = login;
            return this;
        }

        public Builder withPassword(String password) {
            newUser.password = password;
            return this;
        }

        public Builder withEmail(String email) {
            newUser.email = email;
            return this;
        }

        public User build() {
            return newUser;
        }
    }

    User(){
    }

    User(String login, String password){
        this.login = login;
        this.password = password;
    }

    User(String login, String password, String email){
        this.login = login;
        this.password = password;
        this.email = email;
    }
}
