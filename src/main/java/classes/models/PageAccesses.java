package classes.models;

public class PageAccesses {
    private String apexPage;
    private Boolean enabled;

    public String getApexPage() {
        return apexPage;
    }

    public void setApexPage(String apexPage) {
        this.apexPage = apexPage;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "PageAccesses{" +
                "apexPage='" + apexPage + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}
