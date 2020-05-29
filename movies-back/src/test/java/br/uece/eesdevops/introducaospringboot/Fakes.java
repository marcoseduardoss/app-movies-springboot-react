package br.uece.eesdevops.introducaospringboot;

import br.uece.eesdevops.introducaospringboot.domain.entity.Movie;
import br.uece.eesdevops.introducaospringboot.domain.entity.Rating;
import br.uece.eesdevops.introducaospringboot.domain.entity.Movie;
import br.uece.eesdevops.introducaospringboot.domain.entity.Student;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

final class Fakes {

    private Fakes() { }

    static Movie fakeBook() {
        Movie book = new Movie();
        book.setId(1);
        book.setIsbn(generateFakeIsbn());
        book.setTitle("Fake Book");
        book.setAuthor("Fake Author");
        book.setPublicationYear(1999);
        return book;
    }

    static Movie fakeBookWithNoId() {
        Movie book = fakeBook();
        book.setId(null);
        return book;
    }

    private static String generateFakeIsbn() {
        Random random = new Random();
        StringBuilder isbn = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            isbn.append(random.nextInt(10));
        }

        return isbn.toString();
    }

    static Student fakeStudent() {
        Student student = new Student();
        student.setId(1);
        student.setName("Fake Student");
        student.setEmail("fake.student@fakeschool.com");
        student.setEnrollment(generateFakeEnrollment());
        return student;
    }

    static Student fakeStudentWithNoId() {
        Student student = fakeStudent();
        student.setId(null);
        return student;
    }

    private static String generateFakeEnrollment() {
        Random random = new Random();
        StringBuilder isbn = new StringBuilder();

        for (int i = 0; i < 12; i++) {
            isbn.append(random.nextInt(10));
        }

        return isbn.toString();
    }

    static Rating fakeBookLending() {
        Rating bookLending = new Rating();
        bookLending.setId(1);
        bookLending.setBook(fakeBook());
        bookLending.setStudent(fakeStudent());
        bookLending.setStatus(BookLendingStatus.LENT);
        return bookLending;
    }

    static Rating fakeReturnedBookLending() {
        Rating bookLending = new Rating();
        bookLending.setId(1);
        bookLending.setBook(fakeBook());
        bookLending.setStudent(fakeStudent());
        bookLending.setStatus(BookLendingStatus.RETURNED);
        return bookLending;
    }

    static Rating fakeBookLendingWithNoId() {
        Rating bookLending = fakeBookLending();
        bookLending.setId(null);
        return bookLending;
    }

    static Rating fakeBookLending(Movie book, Student student) {
        Rating bookLending = new Rating();
        bookLending.setId(1);
        bookLending.setBook(book);
        bookLending.setStudent(student);
        bookLending.setStatus(BookLendingStatus.LENT);
        return bookLending;
    }

    static Rating fakeBookLendingWithNoId(Movie book, Student student) {
        Rating bookLending = fakeBookLending(book, student);
        bookLending.setId(null);
        return bookLending;
    }

    static Rating fakeBookLendingWithNoId(Movie book, Student student, BookLendingStatus status) {
        Rating bookLending = fakeBookLending(book, student);
        bookLending.setId(null);
        bookLending.setStatus(status);
        return bookLending;
    }

    static List<Rating> fakeBookLendingList() {
        List<Rating> bookLendings = new ArrayList<>();
        bookLendings.add(fakeBookLending());
        return bookLendings;
    }

    static List<Rating> fakeBookLendingListWithLentAndReturned() {
        List<Rating> bookLendings = new ArrayList<>();
        bookLendings.add(fakeBookLending());
        bookLendings.add(fakeReturnedBookLending());
        return bookLendings;
    }

}
