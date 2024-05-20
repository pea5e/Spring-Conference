package ma.emsi.conferences;

public enum EnvLinks {
    CLIENT("http://127.0.0.1:8080"),SERVER("http://127.0.0.1:8080");
    private EnvLinks(final String URL)
    {
        this.url = URL;
    }
    private String url;

    public String URL(){
        return this.url;
    }
}