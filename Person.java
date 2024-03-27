import java.time.LocalDate;
import java.util.Scanner;

import static java.lang.Math.pow;

public class Person {
    private String name; private String surname; private int age;
    private String patronymic; private String birthday; private String sex;
    private int birthYear; private int birthMonth; private int birthDay;
    private Scanner sc;

    public Person() {
        System.out.println("Введите Ваше ФИО и дату рождения в формате: Фамилия Имя Отчество дд.мм.гггг");
        sc = new Scanner(System.in);
        surname = sc.next();
        name = sc.next();
        patronymic = sc.next();
        birthday = sc.next();
        checkSex();
        defineAge();
    }

    public String getName() { return name; }

    public String getSurname() { return surname; }

    public String getPatronymic() { return patronymic; }

    public int getAge() { return age; }

    public String getSex() { return sex; }

    public int getBirthYear() { return birthYear; }

    public int getBirthMonth() { return birthMonth; }

    public int getBirthDay() { return birthDay; }

    public String getFullBirthday() { return birthday; }
    public void setName() {
        System.out.print("Введите имя: ");
        this.name = sc.next();
    }
    public void setSurname() {
        System.out.print("Введите фамилию: ");
        this.surname = sc.next();
    }
    public void setPatronymic() {
        System.out.print("Введите отчество: ");
        this.patronymic = sc.next();
        checkSex();
    }

    public void setBirthday() {
        System.out.print("Введите дату рождения: ");
        this.birthday = sc.next();
        birthDay = 0;
        birthMonth = 0;
        birthYear = 0;
        defineAge();
    }
    private void checkSex() {     // определяем пол человека по окончанию отчества
        if (patronymic.charAt(patronymic.length() - 1) == 'а') {
            sex = "Женский";
        }
        else {
            sex = "Мужской";
        }
    }

    private void defineAge() {     // определяем возраст человека относительно сегодняшней даты
        LocalDate date = LocalDate.now();
        int curYear = date.getYear();
        int curMonth = date.getMonthValue();
        int curDay = date.getDayOfMonth();

        int index = 0;
        for (int i = 1; i >= 0; i--) {
            birthDay += (int)pow(10, i) * (birthday.codePointAt(index) - 48);
            index++;
        }
        index++;
        for (int i = 1; i >= 0; i--) {
            birthMonth += (int)pow(10, i) * (birthday.codePointAt(index) - 48);
            index++;
        }
        index++;
        for (int i = 3; i >= 0; i--) {
            birthYear += (int)pow(10, i) * (birthday.codePointAt(index) - 48);
            index++;
        }

        if (curMonth > birthMonth) {
            age = curYear - birthYear;
        }
        else if (curMonth < birthMonth) {
            age = curYear - birthYear - 1;
        }
        else {
            if (curDay >= birthDay)
            {
                age = curYear - birthYear;
            }
            else {
                age = curYear - birthYear - 1;
            }
        }
    }
    public void printInfo() {     // выводим информацию о человеке: фамилию, инициалы, пол и возраст
        //System.out.println(result);
        System.out.println(surname + " " + name.charAt(0) + "." + patronymic.charAt(0) + ".\nПол: " + sex + "\nВозраст: " + age);
    }
}
