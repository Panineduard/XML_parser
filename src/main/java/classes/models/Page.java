package classes.models;

import com.sun.deploy.util.StringUtils;

import java.util.List;

public class Page implements Comparable{
    private String name;
    private List<String> profiles;

    public Page(String name, List<String> profiles) {
        this.name = name;
        this.profiles = profiles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<String> profiles) {
        this.profiles = profiles;
    }

    @Override
    public String toString() {
        return name +
                ": " + StringUtils.join(profiles, ", ");
    }

    @Override
    public int compareTo(Object o) {
        return this.name.compareTo(((Page)o).name);
    }
}
