package ma.emsi.conferences.Auth;

public enum Role {

    ORG("ORG"),USER("USER");
    private Role(final String REFERER)
    {
        this.ref = REFERER;
    }
    private String ref;

    public String ROLE(){
        return this.ref;
    }

}
