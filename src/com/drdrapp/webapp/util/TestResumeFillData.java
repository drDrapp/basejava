package com.drdrapp.webapp.util;

import com.drdrapp.webapp.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.drdrapp.webapp.model.ContactType.PHONE;

public class TestResumeFillData {

    public static void main(String[] args) {
        Resume resumeTest1;
        resumeTest1 = resumeCreate();
        resumeFillContacts(resumeTest1);
        resumeFillSectionText(resumeTest1);
        resumeFillSectionList(resumeTest1);
        resumeFillSectionOrganization(resumeTest1);
        System.out.println(resumeTest1);
        System.out.println("[Контакты]");
        for (var entry : resumeTest1.getContacts().entrySet()) {
            System.out.println(entry.getKey().getTitle() + ": " + entry.getValue());
        }
        for (var entry : resumeTest1.getSections().entrySet()) {
            System.out.println("[" + entry.getKey().getTitle() + "]");
            System.out.println(entry.getValue());
        }
    }

    private static Resume resumeCreate() {
        return new Resume("Иван Васильевич");
    }

    private static void resumeFillContacts(Resume r) {
        r.addContact(PHONE, "+7 (495) 606–36–02");
        r.addContact(ContactType.PHONE_MOBILE, "+7 (920) 777-77-777");
        r.addContact(ContactType.PHONE_HOME, "+7 (495) 625-35-81");
        r.addContact(ContactType.SKYPE, "skype:ivan1530");
        r.addContact(ContactType.EMAIL, "uasya@yandex.ru");
        r.addContact(ContactType.LINKEDIN, "https://www.linkedin.com/in/ivan1530");
        r.addContact(ContactType.GITHUB, "https://github.com/ivan1530");
        r.addContact(ContactType.STACKOVERFLOW, "https://ru.stackoverflow.com/users/195775/%D0%98%D0%B2%D0%B0%D0%BD-%D0%93%D1%80%D0%BE%D0%B7%D0%BD%D1%8B%D0%B9");
        r.addContact(ContactType.HOME_PAGE, "https://ru.wikipedia.org/wiki/%D0%98%D0%B2%D0%B0%D0%BD_%D0%93%D1%80%D0%BE%D0%B7%D0%BD%D1%8B%D0%B9");
    }

    private static void resumeFillSectionText(Resume r) {
        r.addSection(SectionType.OBJECTIVE, new SectionText("Государь, Царь и Великий князь всея Руси"));
        r.addSection(SectionType.PERSONAL, new SectionText("Великий, мудрый, креативный. Тиран."));
    }

    private static void resumeFillSectionList(Resume r) {
        final List<String> listAchievement = new ArrayList<>();
        listAchievement.add("Увеличение территории государства в 2 раза.");
        listAchievement.add("Увеличение населения на 30-50%.");
        listAchievement.add("Построено 155 новых городов и крепостей.");
        listAchievement.add("Создание в России первой регулярной армии – стрелецкого войска.");
        listAchievement.add("Создана система представительской демократии.");
        listAchievement.add("Создано книгопечатание и системное школьное образование.");
        r.addSection(SectionType.ACHIEVEMENT, new SectionList(listAchievement));
        final List<String> listQualifications = new ArrayList<>();
        listQualifications.add("Язык русский");
        listQualifications.add("Язык татаро-монгольский");
        listQualifications.add("Язык шведский");
        r.addSection(SectionType.QUALIFICATIONS, new SectionList(listQualifications));
    }

    private static void resumeFillSectionOrganization(Resume r) {
        SectionOrganizations sectionExperience = new SectionOrganizations();
        r.addSection(SectionType.EXPERIENCE, sectionExperience);
        sectionExperience.addItem(new Organization(
                "Правительство России",
                "http://government.ru/",
                "Государь, Царь и Великий князь всея Руси",
                "Коронация 16 января 1547. Титул учреждён, он сам (как Государь и Великий князь всея Руси)",
                LocalDate.of(1541, 1, 16),
                LocalDate.of(1584, 3, 24)
        ));
        sectionExperience.addItem(new Organization(
                "Правительство России",
                "http://government.ru/",
                "Государь и Великий князь Московский и всея Руси",
                "Титул упразднён, он сам (как Государь, Царь и Великий князь всея Руси)",
                LocalDate.of(1533, 12, 3),
                LocalDate.of(1541, 1, 16)
        ));
        SectionOrganizations sectionEducation = new SectionOrganizations();
        r.addSection(SectionType.EDUCATION, sectionEducation);
        sectionEducation.addItem(new Organization(
                "Казанские походы",
                "",
                "Полководец",
                "Историю казанских походов часто отсчитывают от похода, состоявшегося в 1545 году, " +
                        "который «носил характер военной демонстрации и усилил позиции „московской партии“ " +
                        "и др. противников хана Сафа-Гирея». Москва поддержала лояльного Руси касимовского правителя " +
                        "Шах-Али, который, став казанским ханом, одобрил проект унии с Москвой. Но в 1546 году " +
                        "Шах-Али был изгнан казанской знатью, которая возвела на трон хана Сафа-Гирея из враждебно " +
                        "настроенной к Руси династии. После этого было решено перейти к активным действиям и " +
                        "устранить угрозу, исходящую от Казани.",
                LocalDate.of(1547, 1, 1),
                LocalDate.of(1552, 12, 31)
        ));
        sectionEducation.addItem(new Organization(
                "Астраханские походы",
                "",
                "Полководец",
                "В начале 1550-х годов Астраханское ханство являлось союзником крымского хана, " +
                        "контролируя нижнее течение Волги. До окончательного подчинения Астраханского ханства " +
                        "при Иване IV было совершено два похода.",
                LocalDate.of(1554, 1, 1),
                LocalDate.of(1556, 12, 31)
        ));
        sectionEducation.addItem(new Organization(
                "Война со Швецией",
                "",
                "Полководец",
                "В годы правления Ивана Грозного были установлены торговые отношения России с Англией через " +
                        "Белое море и Северный Ледовитый океан, сильно ударившие по экономическим интересам Швеции, " +
                        "получавшей немалые доходы от транзитной русско-европейской торговли. В 1553 году " +
                        "экспедиция английского мореплавателя Ричарда Ченслера обогнула Кольский полуостров, вошла в " +
                        "Белое море и бросила якорь к западу от Николо-Корельского монастыря напротив селения Нёнокса. " +
                        "Получив весть о появлении англичан в пределах своей страны, Иван IV пожелал встретиться с " +
                        "Ченслером, который, преодолев около 1000км, с почестями прибыл в Москву. Вскоре после " +
                        "этой экспедиции в Лондоне была основана «Московская компания», получившая впоследствии " +
                        "монопольные торговые права от царя Ивана.",
                LocalDate.of(1554, 1, 1),
                LocalDate.of(1557, 12, 31)
        ));

    }
}