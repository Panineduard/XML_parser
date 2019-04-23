package classes.models;

import java.util.List;

public class Profile {
    private List<PageAccesses> pageAccesses;
    private String name;

    public List<PageAccesses> getPageAccesses() {
        return pageAccesses;
    }

    public void setPageAccesses(List<PageAccesses> pageAccesses) {
        this.pageAccesses = pageAccesses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Profile{ name='" + name + "\"," +
                "pageAccesses=" + pageAccesses + '}';
    }
}
