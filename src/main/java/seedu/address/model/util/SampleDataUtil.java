package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonType;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
                Person.newPerson(new Name("Alex Yeoh"), new Phone("87438807"),
                                new Email("alexyeoh@example.com"),
                                new Address("Blk 30 Geylang Street 29, #06-40"),
                                getTagSet("friends"), PersonType.PARENT),
                Person.newPerson(new Name("Bernice Yu"), new Phone("99272758"),
                                new Email("berniceyu@example.com"),
                                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                                getTagSet("colleagues", "friends"),
                                PersonType.TUTOR),
                Person.newPerson(new Name("Charlotte Oliveiro"),
                                new Phone("93210283"),
                                new Email("charlotte@example.com"),
                                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                                getTagSet("neighbours"), PersonType.PARENT),
                Person.newPerson(new Name("David Li"), new Phone("91031282"),
                                new Email("lidavid@example.com"),
                                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                                getTagSet("family"), PersonType.PARENT),
                Person.newPerson(new Name("Irfan Ibrahim"),
                                new Phone("92492021"),
                                new Email("irfan@example.com"),
                                new Address("Blk 47 Tampines Street 20, #17-35"),
                                getTagSet("classmates"), PersonType.PARENT),
                Person.newPerson(new Name("Roy Balakrishnan"),
                                new Phone("92624417"),
                                new Email("royb@example.com"),
                                new Address("Blk 45 Aljunied Street 85, #11-31"),
                                getTagSet("colleagues"), PersonType.PARENT),
                Person.newPerson(new Name("Emma Yeoh"), new Phone("81234567"),
                                new Email("emmayeoh@example.com"),
                                new Address("Blk 30 Geylang Street 29, #06-40"),
                                getTagSet("student"), PersonType.STUDENT),
                Person.newPerson(new Name("Ethan Yeoh"), new Phone("82345678"),
                                new Email("ethanyeoh@example.com"),
                                new Address("Blk 30 Geylang Street 29, #06-40"),
                                getTagSet("student"), PersonType.STUDENT),
                Person.newPerson(new Name("Olivia Oliveiro"), new Phone("83456789"),
                                new Email("olivia@example.com"),
                                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                                getTagSet("student"), PersonType.STUDENT),
                Person.newPerson(new Name("Liam Li"), new Phone("84567890"),
                                new Email("liaml@example.com"),
                                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                                getTagSet("student"), PersonType.STUDENT),
                Person.newPerson(new Name("Sophia Ibrahim"), new Phone("85678901"),
                                new Email("sophiai@example.com"),
                                new Address("Blk 47 Tampines Street 20, #17-35"),
                                getTagSet("student"), PersonType.STUDENT),
                Person.newPerson(new Name("Aarav Balakrishnan"), new Phone("86789012"),
                                new Email("aaravb@example.com"),
                                new Address("Blk 45 Aljunied Street 85, #11-31"),
                                getTagSet("student"), PersonType.STUDENT) };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings).map(Tag::new).collect(Collectors.toSet());
    }

}
