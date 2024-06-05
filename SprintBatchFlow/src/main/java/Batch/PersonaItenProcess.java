package Batch;

import Model.Person;
import org.springframework.batch.item.ItemProcessor;

import java.util.regex.Pattern;

public class PersonaItenProcess implements ItemProcessor<Person, Person> {
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
            Pattern.CASE_INSENSITIVE);
    @Override
    public Person process(Person person) throws Exception {
        final String nombre = person.getNombre().toUpperCase();
        final String email = person.getEmail();
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new Exception("Invalid email format: " + email);
        }
        final Person transformedPerson = new Person();
        transformedPerson.setNombre(nombre);
        transformedPerson.setEmail(email);

        return transformedPerson;
    }
}
