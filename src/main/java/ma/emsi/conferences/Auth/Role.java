package ma.emsi.conferences.Auth;

public enum Role {

    ORG("ORG"),USER("USER"),ADMIN("ADMIN");
    private Role(final String REFERER)
    {
        this.ref = REFERER;
    }
    private String ref;

    public String ROLE(){
        return this.ref;
    }

}
