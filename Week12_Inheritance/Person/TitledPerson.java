public class TitledPerson extends Person {
    private String prefix;
    private String suffix;

    public TitledPerson(String firstName, String lastName, String prefixTitle, String suffixTitle) {
        // every subclass constructor must start with super(), whether implicit or explicit
        super(firstName, lastName); 

        prefix = prefixTitle;
        suffix = suffixTitle;
    }

    // useful for catching runtime errors at compile-time
    @Override
    public String getFullName() {
        String name = super.getFullName();
        
        if(prefix != null) {
            name = prefix + " " + name;
        }

        if(suffix != null) {
            name = name + " " + suffix;
        }

        return name;
    }
}