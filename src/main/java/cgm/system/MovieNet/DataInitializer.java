package cgm.system.MovieNet;

import cgm.system.MovieNet.entity.*;
import cgm.system.MovieNet.repository.*;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.lang.NonNullApi;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer implements ApplicationListener<ApplicationReadyEvent> {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    @Transactional
    public void onApplicationEvent( ApplicationReadyEvent event) {
        if(userRepository.count() == 0 && roleRepository.count() == 0 && movieRepository.count() == 0 && genreRepository.count() == 0 && reviewRepository.count() == 0){
            Role role1 = new Role("Admin");
            Role role2 = new Role("User");
            roleRepository.save(role1);
            roleRepository.save(role2);
            User user = new User("admin","thazinmin9861@gmail.com",passwordEncoder.encode("admin123"),role1);
            User userData = new User("user","cgm.thazinmin@gmail.com",passwordEncoder.encode("admin123"),role2);
            userRepository.save(user);
            userRepository.save(userData);

            //
            // Initialize genres
            Genre action = new Genre("Action");
            genreRepository.save(action);

            Genre drama = new Genre("Drama");
            genreRepository.save(drama);

            Genre comedy = new Genre("Comedy");
            genreRepository.save(comedy);

            Genre pirates = new Genre("Pirates Of The Caribbean");
            genreRepository.save(pirates);

            Genre romance = new Genre("Romance");
            genreRepository.save(romance);

            Genre sci_fi = new Genre("Sci-Fi");
            genreRepository.save(sci_fi);

            Genre stephenChow = new Genre("Stephen Chow");
            genreRepository.save(stephenChow);

            Genre vincent = new Genre("Vincent");
            genreRepository.save(vincent);



            // Initialize movies
            Movie movie1 = new Movie(
                    "13 Hours: The Secret Soldiers of Benghazi",
                    2016,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\hancock.txt"),
                    7.1,
                    "Michael Bay, Silke Engelhardt",
                    "/img/poster/Action/13 hour The secret soldiers of benghazi.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie1Genres = new ArrayList<>();
            movie1Genres.add(action);
            movie1.setGenres(movie1Genres);
            movieRepository.save(movie1);

            Movie movie2;
            movie2 = new Movie(
                    "Hancock",
                    2008,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\hancock.txt"),
                    6.4,
                    "Peter Berg",
                    "/img/poster/Action/Hancock.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie2Genres = new ArrayList<>();
            movie2Genres.add(action);
            movie2.setGenres(movie2Genres);
            movieRepository.save(movie2);

            Movie movie3 = new Movie(
                    "Homefront",
                    2013 ,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\homefront.txt"),
                    6.5,
                    "Elizabeth Ludwick-Bax, Gary Fleder",
                    "/img/poster/Action/Homefront.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie3Genres = new ArrayList<>();
            movie3Genres.add(action);
            movie3.setGenres(movie3Genres);
            movieRepository.save(movie3);

            Movie movie4 = new Movie(
                    "War of the Worlds",
                    2005,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\War of the worlds.txt"),
                    6.5,
                    "Byron Haskin",
                    "/img/poster/Action/War of the Worlds.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie4Genres = new ArrayList<>();
            movie4Genres.add(action);
            movie4.setGenres(movie4Genres);
            movieRepository.save(movie4);

            Movie movie5 = new Movie(
                    "Confidential Assignment",
                    2017,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\confidential assigment.txt"),
                    6.7,
                    "Kim Sung-hoon",
                    "/img/poster/Action/Confidential Assignment.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie5Genres = new ArrayList<>();
            movie5Genres.add(action);
            movie5.setGenres(movie5Genres);
            movieRepository.save(movie5);

            Movie movie6 = new Movie(
                    "Dragon Inn",
                    1967,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\dragon inn.txt"),
                    7.4,
                    "King Hu",
                    "/img/poster/Action/Dragon Inn.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie6Genres = new ArrayList<>();
            movie6Genres.add(action);
            movie6.setGenres(movie6Genres);
            movieRepository.save(movie6);

            Movie movie7 = new Movie(
                    "Furiosa: A Mad Max Saga",
                    2024,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Furiosa A Mad Max Saga.txt"),
                    7.8,
                    "George Miller",
                    "/img/poster/Action/Furiosa.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie7Genres = new ArrayList<>();
            movie7Genres.add(action);
            movie7.setGenres(movie7Genres);
            movieRepository.save(movie7);

            Movie movie8 = new Movie(
                    "Garudan",
                    2024,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Garudan (2024).txt"),
                    7.6,
                    "Arun Varma",
                    "/img/poster/Action/Garudan.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie8Genres = new ArrayList<>();
            movie8Genres.add(action);
            movie8.setGenres(movie8Genres);
            movieRepository.save(movie8);

            Movie movie9 = new Movie(
                    "Jungle Chase",
                    2024,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Jungle Chase ( 2024 ).txt"),
                    5.7,
                    "Jicheng Zou",

                    "/img/poster/Action/Jungle Chase.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie9Genres = new ArrayList<>();
            movie9Genres.add(action);
            movie9.setGenres(movie9Genres);
            movieRepository.save(movie9);

            Movie movie10 = new Movie(
                    "Legacy of Rage",
                    1986,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Legacy of Rage (1986).txt"),
                    6.0,
                    "Ronny Yu",
                    "/img/poster/Action/Legacy Of Rage.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie10Genres = new ArrayList<>();
            movie10Genres.add(action);
            movie10.setGenres(movie10Genres);
            movieRepository.save(movie10);

            Movie movie11 = new Movie(
                    "Master of Maoshan",
                    2021,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Master of Maoshan ( 2021 ).txt"),
                    5.3,
                    "Chen Xiong",
                    "/img/poster/Action/Master of Mao Shan.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie11Genres = new ArrayList<>();
            movie11Genres.add(action);
            movie11.setGenres(movie11Genres);
            movieRepository.save(movie11);

            Movie movie12 = new Movie(
                    "Mayhem!",
                    2023,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Mayhem! (2023).txt"),
                    6.3,
                    "Xavier Gens",
                    "/img/poster/Action/Mayhem.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie12Genres = new ArrayList<>();
            movie12Genres.add(action);
            movie12.setGenres(movie12Genres);
            movieRepository.save(movie12);

            Movie movie13 = new Movie(
                    "Mr. Vampire 3",
                    1987,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Mr. Vampire 3 (1987).txt"),
                    6.7,
                    "Ricky Lau",
                    "/img/poster/Action/Mr.Vampire 3.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie13Genres = new ArrayList<>();
            movie13Genres.add(action);
            movie13.setGenres(movie13Genres);
            movieRepository.save(movie13);

            Movie movie14 = new Movie(
                    "Mr. Vampire Saga 4",
                    1988,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Mr. Vampire Saga 4 ( 1988 ).txt"),
                    6.3,
                    "Ricky Lau",
                    "/img/poster/Action/Mr.Vampire 4.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie14Genres = new ArrayList<>();
            movie14Genres.add(action);
            movie14.setGenres(movie14Genres);
            movieRepository.save(movie14);

            Movie movie15 = new Movie(
                    "Mr. Vampire",
                    1985,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Mr. Vampire ( 1985 ).txt"),
                    7.3,
                    "Ricky Lau",
                    "/img/poster/Action/Mr.Vampire 1.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie15Genres = new ArrayList<>();
            movie15Genres.add(action);
            movie15.setGenres(movie15Genres);
            movieRepository.save(movie15);

            Movie movie16 = new Movie(
                    "My Father Is A Hero",
                    1995,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\My Father Is A Hero ( 1995 ).txt"),
                    6.5,
                    "Corey Yuen",
                    "/img/poster/Action/My Father Is A Hero.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie16Genres = new ArrayList<>();
            movie16Genres.add(action);
            movie16.setGenres(movie16Genres);
            movieRepository.save(movie16);

            Movie movie17 = new Movie(
                    "Sheriff: Narko Integriti",
                    2024,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Sheriff Narko Integriti (2024).txt"),
                    7.6,
                    "Syafiq Yusof",
                    "/img/poster/Action/Sheriff Narko Integriti.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie17Genres = new ArrayList<>();
            movie17Genres.add(action);
            movie17.setGenres(movie17Genres);
            movieRepository.save(movie17);

            Movie movie18 = new Movie(
                    "Showdown in Little Tokyo",
                    1991,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Showdown in Little Tokyo (1991).txt"),
                    6.1,
                    "Mark L.Lester",
                    "/img/poster/Action/Showdown In Little Tokyo.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie18Genres = new ArrayList<>();
            movie18Genres.add(action);
            movie18.setGenres(movie18Genres);
            movieRepository.save(movie18);

            Movie movie19 = new Movie(
                    "The Gangster, the Cop, the Devil",
                    2019,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\The Gangster, the Cop, the Devil (2019).txt"),
                    6.9,
                    "Lee Won-tae",
                    "/img/poster/Action/The Gangster,The Cop,The Devil.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie19Genres = new ArrayList<>();
            movie19Genres.add(action);
            movie19.setGenres(movie19Genres);
            movieRepository.save(movie19);



            Movie movie22 = new Movie(
                    "Aladdin",
                    2019,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Comedy\\Aladdin (2019).txt"),
                    7.4,
                    "Guy Ritchie, Max Keene",
                    "/img/poster/Comedy/Aladdin.jpeg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie22Genres = new ArrayList<>();
            movie22Genres.add(comedy);
            movie22.setGenres(movie22Genres);
            movieRepository.save(movie22);

            Movie movie23 = new Movie(
                    "Hot Fuzz",
                    2007,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Comedy\\Hot Fuzz (2007).txt"),
                    7.9,
                    "Edgar Wright",
                    "/img/poster/Comedy/Hot Fuzz.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie23Genres = new ArrayList<>();
            movie23Genres.add(comedy);
            movie23.setGenres(movie23Genres);
            movieRepository.save(movie23);

            Movie movie24 = new Movie(
                    "Johnny English",
                    2003,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Comedy\\Johnny English (2003).txt"),
                    6.2,
                    "Peter Howitt",
                    "/img/poster/Comedy/Johnny Englist 2003.jfif",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie24Genres = new ArrayList<>();
            movie24Genres.add(comedy);
            movie24.setGenres(movie24Genres);
            movieRepository.save(movie24);

            Movie movie25 = new Movie(
                    "Johnny English Reborn",
                    2011,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Comedy\\Johnny English Reborn (2011).txt"),
                    6.3,
                    "Alex Oakley, Andrew McEwan",
                    "/img/poster/Comedy/Johnny English reborn 2013.jfif",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie25Genres = new ArrayList<>();
            movie25Genres.add(comedy);
            movie25.setGenres(movie25Genres);
            movieRepository.save(movie25);

            Movie movie26 = new Movie(
                    "Johnny English Strikes Again",
                    2018,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Comedy\\Johnny English Strikes Again (2018).txt"),
                    6.2,
                    "David Kerr",
                    "/img/poster/Comedy/Johnny English Strike again 2018.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie26Genres = new ArrayList<>();
            movie26Genres.add(comedy);
            movie26.setGenres(movie26Genres);
            movieRepository.save(movie26);

            Movie movie27 = new Movie(
                    "50/50",
                    2011,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Comedy\\50 50 (2011).txt"),7.6,
                    "Jonathan Levine",
                    "/img/poster/Comedy/50 50.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie27Genres = new ArrayList<>();
            movie27Genres.add(comedy);
            movie27.setGenres(movie27Genres);
            movieRepository.save(movie27);

            Movie movie28 = new Movie(
                    "Aatmapamphlet",
                    2023,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Comedy\\Aatmapamphlet (2023).txt"),
                    7.9,
                    "Ashish Bende",
                    "/img/poster/Comedy/Aatmapamphlet.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie28Genres = new ArrayList<>();
            movie28Genres.add(comedy);
            movie28.setGenres(movie28Genres);
            movieRepository.save(movie28);

            Movie movie29 = new Movie(
                    "Absolutely Anything",
                    2015,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Comedy\\Absolutely Anything (2015).txt"),
                    6.0,
                    "Terry Jones",
                    "/img/poster/Comedy/Absolutely Anything.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie29Genres = new ArrayList<>();
            movie29Genres.add(comedy);
            movie29.setGenres(movie29Genres);
            movieRepository.save(movie29);

            Movie movie30 = new Movie(
                    "Beau Is Afraid",
                    2023,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Comedy\\Beau Is Afraid (2023).txt"),7.1,
                    "Ari Aster",
                    "/img/poster/Comedy/Beau is Afraid.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie30Genres = new ArrayList<>();
            movie30Genres.add(comedy);
            movie30.setGenres(movie30Genres);
            movieRepository.save(movie30);

            Movie movie31 = new Movie(
                    "Big Daddy",
                    1999,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Comedy\\Big Daddy (1999).txt"),
                    6.4,
                    "Dennis Dugan",
                    "/img/poster/Comedy/Big Daddy.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie31Genres = new ArrayList<>();
            movie31Genres.add(comedy);
            movie31.setGenres(movie31Genres);
            movieRepository.save(movie31);

            Movie movie32 = new Movie(
                    "City Lights",
                    1931,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Comedy\\City Lights (1931).txt"),
                    8.5,
                    "Charles Chaplin",
                    "/img/poster/Comedy/City Light.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie32Genres = new ArrayList<>();
            movie32Genres.add(comedy);
            movie32.setGenres(movie32Genres);
            movieRepository.save(movie32);

            Movie movie33 = new Movie(
                    "Demolition",
                    2015,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Comedy\\Demolition (2015).txt"),
                    7.0,
                    "Jean-Marc Vallée",
                    "/img/poster/Comedy/Demolition.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie33Genres = new ArrayList<>();
            movie33Genres.add(comedy);
            movie33.setGenres(movie33Genres);
            movieRepository.save(movie33);

            Movie movie34 = new Movie(
                    "Elemental",
                    2023,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Comedy\\Elemental (2023).txt"),
                    7.1,
                    "Peter Sohn",
                    "/img/poster/Comedy/Elemental.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie34Genres = new ArrayList<>();
            movie34Genres.add(comedy);
            movie34.setGenres(movie34Genres);
            movieRepository.save(movie34);

            Movie movie35 = new Movie(
                    "Little Big Soldier",
                    2010,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Comedy\\Little Big Soldier (2010).txt"),
                    6.9,
                    "Ding Sheng",
                    "/img/poster/Comedy/Little big solider.jfif",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie35Genres = new ArrayList<>();
            movie35Genres.add(comedy);
            movie35.setGenres(movie35Genres);
            movieRepository.save(movie35);

            Movie movie36 = new Movie(
                    "Mr. Vampire 2",
                    1986,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Comedy\\Mr. Vampire 2 ( 1986 ).txt"),
                    5.9,
                    "Ricky Lau",
                    "/img/poster/Comedy/Mr. Vampire 2.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie36Genres = new ArrayList<>();
            movie36Genres.add(comedy);
            movie36.setGenres(movie36Genres);
            movieRepository.save(movie36);

            Movie movie37 = new Movie(
                    "Pee Nak 3",
                    2022,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Comedy\\Pee Nak 3 (2022).txt"),
                    6.5,
                    "Phontharis Chotkijsadarsopon",
                    "/img/poster/Comedy/Pee nak 3.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie37Genres = new ArrayList<>();
            movie37Genres.add(comedy);
            movie37.setGenres(movie37Genres);
            movieRepository.save(movie37);

            Movie movie38 = new Movie(
                    "Skiptrace",
                    2016,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Comedy\\Skiptrace (2016).txt")
                    ,5.6,
                    "Renny Harlin",
                    "/img/poster/Comedy/Skip Trace.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie38Genres = new ArrayList<>();
            movie38Genres.add(comedy);
            movie38.setGenres(movie38Genres);
            movieRepository.save(movie38);

            Movie movie39 = new Movie(
                    "The Treasure Protector",
                    2024,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Comedy\\The Treasure Protector ( 2024 ).txt"),
                    5.7,
                    "Danny Ray",
                    "/img/poster/Comedy/The Treasure Protector.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie39Genres = new ArrayList<>();
            movie39Genres.add(comedy);
            movie39.setGenres(movie39Genres);
            movieRepository.save(movie39);

            Movie movie40 = new Movie(
                    "The Maiden Heist",
                    2009,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Comedy\\The Maiden Heist (2009).txt"),
                    6.0,
                    "Peter Hewitt",
                    "/img/poster/Comedy/The Maiden Heist.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie40Genres = new ArrayList<>();
            movie40Genres.add(comedy);
            movie40.setGenres(movie40Genres);
            movieRepository.save(movie40);



            Movie movie43 = new Movie(
                    "Always – Sunset on Third Street",
                    2005,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Comedy\\Always – Sunset on Third Street (2005).txt"),
                    7.7,
                    "Takashi Yamazaki",
                    "/img/poster/Drama/Always Sunset on third Street.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie43Genres = new ArrayList<>();
            movie43Genres.add(drama);
            movie43.setGenres(movie43Genres);
            movieRepository.save(movie43);

            Movie movie44 = new Movie(
                    "City on Fire",
                    1987,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Comedy\\City on Fire (1987).txt"),
                    7.0,
                    "Ringo Lam",
                    "/img/poster/Drama/City On Fire.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie44Genres = new ArrayList<>();
            movie44Genres.add(drama);
            movie44.setGenres(movie44Genres);
            movieRepository.save(movie44);

            Movie movie45 = new Movie(
                    "Gifted",
                    2017,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Comedy\\Gifted (2017).txt"),
                    7.6,
                    "Jon Mallard",
                    "/img/poster/Drama/Gifted.jfif",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie45Genres = new ArrayList<>();
            movie45Genres.add(drama);
            movie45.setGenres(movie45Genres);
            movieRepository.save(movie45);

            Movie movie46 = new Movie(
                    "Shanghai Triad",
                    1995,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Comedy\\Shanghai Triad (1995).txt"),7.1,
                    "Yimou Zhang",
                    "/img/poster/Drama/Shanghai Triad.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie46Genres = new ArrayList<>();
            movie46Genres.add(drama);
            movie46.setGenres(movie46Genres);
            movieRepository.save(movie46);

            Movie movie47 = new Movie(
                    "3:10 to Yuma",
                    2007,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Comedy\\3 10 to Yuma (2007).txt"),
                    7.7,
                    "James Mangold",
                    "/img/poster/Drama/3.10.To.Yuma.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie47Genres = new ArrayList<>();
            movie47Genres.add(drama);
            movie47.setGenres(movie47Genres);
            movieRepository.save(movie47);

            Movie movie48 = new Movie(
                    "Agora",
                    2009,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Comedy\\Agora (2009).txt"),
                    7.1,
                    "Alejandro Amenábar",
                    "/img/poster/Drama/Agora.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie48Genres = new ArrayList<>();
            movie48Genres.add(drama);
            movie48.setGenres(movie48Genres);
            movieRepository.save(movie48);

            Movie movie49 = new Movie(
                    "Battle of the Warriors",
                    2006,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Comedy\\Battle of the Warriors (2006).txt"),
                    6.7,
                    "Chi Leung 'Jacob' Cheung",
                    "/img/poster/Drama/Battle of the warriors.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie49Genres = new ArrayList<>();
            movie49Genres.add(drama);
            movie49.setGenres(movie49Genres);
            movieRepository.save(movie49);

            Movie movie50 = new Movie(
                    "Call Me Chihiro",
                    2023,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Comedy\\Call Me Chihiro (2023).txt"),
                    6.9,
                    "Rikiya Imaizumi",
                    "/img/poster/Drama/Call me Chihiro.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie50Genres = new ArrayList<>();
            movie50Genres.add(drama);
            movie50.setGenres(movie50Genres);
            movieRepository.save(movie50);

            Movie movie51 = new Movie(
                    "Drawing Closer",
                    2024,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Comedy\\Drawing Closer (2024).txt"),
                    7.9,
                    "Takahiro Miki",
                    "/img/poster/Drama/Drawing Closer.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie51Genres = new ArrayList<>();
            movie51Genres.add(drama);
            movie51.setGenres(movie51Genres);
            movieRepository.save(movie51);

            Movie movie52 = new Movie(
                    "Dwelling by the West Lake",
                    2024,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Comedy\\Dwelling by the West Lake ( 2024 ).txt"),
                    6.0,
                    "Gu Xiaogang",
                    "/img/poster/Drama/Dwelling by the West Lake.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie52Genres = new ArrayList<>();
            movie52Genres.add(drama);
            movie52.setGenres(movie52Genres);
            movieRepository.save(movie52);

            Movie movie53 = new Movie(
                    "If Only",
                    2004,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Comedy\\If Only (2004).txt"),
                    7.0,
                    "Gil Junger",
                    "/img/poster/Drama/if only.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie53Genres = new ArrayList<>();
            movie53Genres.add(drama);
            movie53.setGenres(movie53Genres);
            movieRepository.save(movie53);

            Movie movie54 = new Movie(
                    "I’ll Sleep When I’m Dead",
                    2003,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Comedy\\I’ll Sleep When I’m Dead (2003).txt"),
                    5.8,
                    "Mike Hodges",
                    "/img/poster/Drama/I'll Sleep When I'm Dead.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie54Genres = new ArrayList<>();
            movie54Genres.add(drama);
            movie54.setGenres(movie54Genres);
            movieRepository.save(movie54);

            Movie movie55 = new Movie(
                    "Last Night in Soho",
                    2021,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Comedy\\Last Night in Soho (2021).txt"),
                    7.5,
                    "Edgar Wright",
                    "/img/poster/Drama/Last Night in Soho.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie55Genres = new ArrayList<>();
            movie55Genres.add(drama);
            movie55.setGenres(movie55Genres);
            movieRepository.save(movie55);

            Movie movie56 = new Movie(
                    "My Left Foot: The Story of Christy Brown",
                    1989,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Comedy\\My Left Foot The Story of Christy Brown (1989).txt"),
                    7.8,
                    "Jim Sheridan",
                    "/img/poster/Drama/My Left Foot.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie56Genres = new ArrayList<>();
            movie56Genres.add(drama);
            movie56.setGenres(movie56Genres);
            movieRepository.save(movie56);

            Movie movie57 = new Movie(
                    "Phalana Abbayi Phalana Ammayi",
                    2023,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Comedy\\Phalana Abbayi Phalana Ammayi (2023).txt"),
                    5.0,
                    "Srinivas Avasarala",
                    "/img/poster/Drama/Phalana Abbayi Phalana Ammayi.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie57Genres = new ArrayList<>();
            movie57Genres.add(drama);
            movie57.setGenres(movie57Genres);
            movieRepository.save(movie57);

            Movie movie58 = new Movie(
                    "The Peasants",
                    2023,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Comedy\\The Peasants (2023).txt"),
                    7.8,
                    "DK Welchman, Hugh Welchman",
                    "/img/poster/Drama/The Peasants.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie58Genres = new ArrayList<>();
            movie58Genres.add(drama);
            movie58.setGenres(movie58Genres);
            movieRepository.save(movie58);

            Movie movie59 = new Movie(
                    "The Fabulous Baker Boys",
                    1989,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Comedy\\The Fabulous Baker Boys (1989).txt"),
                    6.9,
                    "Steve Kloves",
                    "/img/poster/Drama/The Fabulous Baker boys.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie59Genres = new ArrayList<>();
            movie59Genres.add(drama);
            movie59.setGenres(movie59Genres);
            movieRepository.save(movie59);

            Movie movie60 = new Movie(
                    "The Name of the Rose",
                    1986,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Comedy\\The Name of the Rose (1986).txt"),
                    7.7,
                    "Jean-Jacques Annaud",
                    "/img/poster/Drama/The name of the rose.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie60Genres = new ArrayList<>();
            movie60Genres.add(drama);
            movie60.setGenres(movie60Genres);
            movieRepository.save(movie60);

            Movie movie61 = new Movie(
                    "The Yards",
                    2000,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Comedy\\The Yards (2000).txt"),
                    6.4,
                    "James Gray",
                    "/img/poster/Drama/The Yards.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie61Genres = new ArrayList<>();
            movie61Genres.add(drama);
            movie61.setGenres(movie61Genres);
            movieRepository.save(movie61);



            Movie movie63 = new Movie(
                    "Pirates of the Caribbean: At World’s End",
                    2007,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Pirates\\Pirates of the Caribbean At World’s End(2007).txt"),
                    7.1,
                    "Gore Verbinski",
                    "/img/poster/Pirates Of The Caribbean/At World's End.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie63Genres = new ArrayList<>();
            movie63Genres.add(pirates);
            movie63.setGenres(movie63Genres);
            movieRepository.save(movie63);

            Movie movie64 = new Movie(
                    "Pirates of the Caribbean: Dead Men Tell No Tales",
                    2017,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Pirates\\Pirates of the Caribbean Dead Men Tell No Tales.txt"),
                    6.6,
                    "Carolina Häggström, Espen Sandberg, Joachim Rønning, Joecy Shepherd, Victoria Sullivan",
                    "/img/poster/Pirates Of The Caribbean/Dead Men Tell No Tales.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie64Genres = new ArrayList<>();
            movie64Genres.add(pirates);
            movie64.setGenres(movie64Genres);
            movieRepository.save(movie64);

            Movie movie65 = new Movie(
                    "Pirates of the Caribbean: The Curse of the Black Pearl",
                    2003,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Pirates\\Pirates of the Caribbean The Curse of the Black Pearl.txt"),
                    8.0,
                    "Gary Romolo Fiorelli, Gore Verbinski, Peter Kohn, Susan J. Hellmann",
                    "/img/poster/Pirates Of The Caribbean/The Curse of the Black Pearl.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie65Genres = new ArrayList<>();
            movie65Genres.add(pirates);
            movie65.setGenres(movie65Genres);
            movieRepository.save(movie65);

            Movie movie66 = new Movie(
                    "Pirates of the Caribbean: On Stranger Tides",
                    2011,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Pirates\\Pirates of the Caribbean On Stranger Tides(2011).txt"),
                    6.6,
                    "Kerry Lyn McKissick, Rob Marshall",
                    "/img/poster/Pirates Of The Caribbean/On Stranger Tides.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie66Genres = new ArrayList<>();
            movie66Genres.add(pirates);
            movie66.setGenres(movie66Genres);
            movieRepository.save(movie66);

            Movie movie67 = new Movie(
                    "Crossing Hennessy",
                    2010,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Pirates\\Crossing Hennessy (2010).txt"),
                    6.3,
                    "Ivy HO",
                    "/img/poster/Romance/Crossing Hennessy.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie67Genres = new ArrayList<>();
            movie67Genres.add(romance);
            movie67.setGenres(movie67Genres);
            movieRepository.save(movie67);

            Movie movie68 = new Movie(
                    "Our Secret Diary",
                    2023,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Pirates\\Our Secret Diary (2023).txt"),
                    7.5,
                    "Kentaro Takemura",
                    "/img/poster/Romance/Our Secret Diary.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie68Genres = new ArrayList<>();
            movie68Genres.add(romance);
            movie68.setGenres(movie68Genres);
            movieRepository.save(movie68);

            Movie movie69 = new Movie(
                    "The Remains of the Day",
                    1993,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Pirates\\The Remains of the Day (1993).txt"),
                    7.8,
                    "James Ivory",
                    "/img/poster/Romance/the remains of the day.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie69Genres = new ArrayList<>();
            movie69Genres.add(romance);
            movie69.setGenres(movie69Genres);
            movieRepository.save(movie69);

            Movie movie70 = new Movie(
                    "Wings of Desire",
                    1987,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Romance\\Wings of Desire (1987).txt"),
                    7.9,
                    "Wim Wenders",
                    "/img/poster/Romance/wings of desire.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie70Genres = new ArrayList<>();
            movie70Genres.add(romance);
            movie70.setGenres(movie70Genres);
            movieRepository.save(movie70);

            Movie movie71 = new Movie(
                    "A Beautiful Life",
                    2023,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Romance\\A Beautiful Life (2023).txt"),
                    6.4,
                    "Mehdi Avaz",
                    "/img/poster/Romance/a beautiful life.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie71Genres = new ArrayList<>();
            movie71Genres.add(romance);
            movie71.setGenres(movie71Genres);
            movieRepository.save(movie71);

            Movie movie72 = new Movie(
                    "A Girl in My Room",
                    2022,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Romance\\A Girl in My Room (2022).txt"),
                    6.6,
                    "Natsuki Takahashi",
                    "/img/poster/Romance/a girl in my room.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie72Genres = new ArrayList<>();
            movie72Genres.add(romance);
            movie72.setGenres(movie72Genres);
            movieRepository.save(movie72);

            Movie movie73 = new Movie(
                    "Almost Love",
                    2022,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Romance\\Almost Love ( 2022 ).txt"),
                    6.0,
                    "Luo Luo",
                    "/img/poster/Romance/Almost love.jfif",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie73Genres = new ArrayList<>();
            movie73Genres.add(romance);
            movie73.setGenres(movie73Genres);
            movieRepository.save(movie73);

            Movie movie74 = new Movie(
                    "Ditto",
                    2000,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Romance\\Ditto (2000).txt"),
                    7.2,
                    "Kim Jeong-kwon",
                    "/img/poster/Romance/Ditto.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie74Genres = new ArrayList<>();
            movie74Genres.add(romance);
            movie74.setGenres(movie74Genres);
            movieRepository.save(movie74);

            Movie movie75 = new Movie(
                    "I Miss You",
                    2024,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Romance\\I Miss You ( 2024 ).txt"),
                    5.9,
                    "Han Yan",
                    "/img/poster/Romance/I miss you.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie75Genres = new ArrayList<>();
            movie75Genres.add(romance);
            movie75.setGenres(movie75Genres);
            movieRepository.save(movie75);

            Movie movie76 = new Movie(
                    "Just Only Love",
                    2018,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Romance\\Just Only Love (2018).txt"),
                    6.5,
                    "Rikiya Imaizumi",
                    "/img/poster/Romance/just only love.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie76Genres = new ArrayList<>();
            movie76Genres.add(romance);
            movie76.setGenres(movie76Genres);
            movieRepository.save(movie76);

            Movie movie77 = new Movie(
                    "Mai",
                    2024,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Romance\\Mai (2024).txt"),
                    6.9,
                    "Tran Thanh",
                    "/img/poster/Romance/Mai.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie77Genres = new ArrayList<>();
            movie77Genres.add(romance);
            movie77.setGenres(movie77Genres);
            movieRepository.save(movie77);

            Movie movie78 = new Movie(
                    "My Best Summer",
                    2019,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Romance\\My Best Summer ( 2019 ).txt"),
                    6.0,
                    "Zhang Di Sha",
                    "/img/poster/Romance/my best summer.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie78Genres = new ArrayList<>();
            movie78Genres.add(romance);
            movie78.setGenres(movie78Genres);
            movieRepository.save(movie78);

            Movie movie79 = new Movie(
                    "My Name Is Loh Kiwan",
                    2024,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Romance\\My Name Is Loh Kiwan (2024).txt"),
                    5.9,
                    "Kim Hee Jin",
                    "/img/poster/Romance/My name is loh kiwan.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie79Genres = new ArrayList<>();
            movie79Genres.add(romance);
            movie79.setGenres(movie79Genres);
            movieRepository.save(movie79);

            Movie movie80 = new Movie(
                    "Neetho",
                    2022,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Romance\\Neetho (2022).txt"),
                    8.9,
                    "Balu Sharma",
                    "/img/poster/Romance/Neetho.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie80Genres = new ArrayList<>();
            movie80Genres.add(romance);
            movie80.setGenres(movie80Genres);
            movieRepository.save(movie80);

            Movie movie81 = new Movie(
                    "Single in Seoul",
                    2023,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Romance\\Single in Seoul(2023).txt"),
                    7.0,
                    "Park Beom-su",
                    "/img/poster/Romance/single in seoul.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie81Genres = new ArrayList<>();
            movie81Genres.add(romance);
            movie81.setGenres(movie81Genres);
            movieRepository.save(movie81);

            Movie movie82 = new Movie(
                    "Starfish",
                    2023,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Romance\\Starfish (2023).txt"),
                    8.6,
                    "Akhilesh Jaiswal",
                    "/img/poster/Romance/star fish.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie82Genres = new ArrayList<>();
            movie82Genres.add(romance);
            movie82.setGenres(movie82Genres);
            movieRepository.save(movie82);

            Movie movie83 = new Movie(
                    "The Greatest Hits",
                    2024,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Romance\\The Greatest Hits (2024).txt"),
                    6.2,
                    "Ned Benson",
                    "/img/poster/Romance/the greatest hits.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie83Genres = new ArrayList<>();
            movie83Genres.add(romance);
            movie83.setGenres(movie83Genres);
            movieRepository.save(movie83);

            Movie movie84 = new Movie(
                    "The Illusionist",
                    2006,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Romance\\The Illusionist (2006).txt"),
                    7.5,
                    "Neil Burger",
                    "/img/poster/Romance/the illusionist.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie84Genres = new ArrayList<>();
            movie84Genres.add(romance);
            movie84.setGenres(movie84Genres);
            movieRepository.save(movie84);

            Movie movie85 = new Movie(
                    "The Last Letter from Your Lover",
                    2021,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Romance\\The Last Letter from Your Lover(2021).txt"),
                    6.7,
                    "Augustine Frizzell",
                    "/img/poster/Romance/The Last Letter From Your Lover.jfif",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie85Genres = new ArrayList<>();
            movie85Genres.add(romance);
            movie85.setGenres(movie85Genres);
            movieRepository.save(movie85);



            Movie movie87 = new Movie(
                    "Equilibrium",
                    2002,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Sci-fi\\Equilibrium (2002).txt"),
                    7.3,
                    "Kurt Wimmer, Mark Taylor, Pamela Alch, Stella D'Onofrio, Tess Malone",
                    "/img/poster/Sci-fi/Equilibrium.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie87Genres = new ArrayList<>();
            movie87Genres.add(romance);
            movie87.setGenres(movie87Genres);
            movieRepository.save(movie87);

            Movie movie88 = new Movie(
                    "Passengers",
                    2016,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Sci-fi\\Passengers (2016).txt"),
                    7.0,
                    "Ana Maria Quintana, Dawn Massaro",
                    "/img/poster/Sci-fi/Passengers.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie88Genres = new ArrayList<>();
            movie88Genres.add(sci_fi);
            movie88.setGenres(movie88Genres);
            movieRepository.save(movie88);

            Movie movie89 = new Movie(
                    "Finch",
                    2021,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Sci-fi\\Finch (2021).txt"),
                    7.3,
                    "Miguel Sapochnik",
                    "/img/poster/Sci-fi/Finch.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie89Genres = new ArrayList<>();
            movie89Genres.add(sci_fi);
            movie89.setGenres(movie89Genres);
            movieRepository.save(movie89);

            Movie movie90 = new Movie(
                    "Twelve Monkeys",
                    1995,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Sci-fi\\Twelve Monkeys (1995).txt"),
                    8.0,
                    "Andrew Bernstein, Marilyn Bailey",
                    "/img/poster/Sci-fi/12 Monkeys.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie90Genres = new ArrayList<>();
            movie90Genres.add(sci_fi);
            movie90.setGenres(movie90Genres);
            movieRepository.save(movie90);

            Movie movie91 = new Movie(
                    "7:11 PM",
                    2023,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Sci-fi\\7 11 PM (2023).txt"),
                    7.4,
                    "Chaitu Madala",
                    "/img/poster/Sci-fi/7 11 PM.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie91Genres = new ArrayList<>();
            movie91Genres.add(sci_fi);
            movie91.setGenres(movie91Genres);
            movieRepository.save(movie91);

            Movie movie92 = new Movie(
                    "A Million Days",
                    2023,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Sci-fi\\A Million Days (2023).txt"),
                    4.2,
                    "Mitch Jenkins",
                    "/img/poster/Sci-fi/A Million Days.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie92Genres = new ArrayList<>();
            movie92Genres.add(sci_fi);
            movie92.setGenres(movie92Genres);
            movieRepository.save(movie92);

            Movie movie93 = new Movie(
                    "Ancika",
                    2024,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Sci-fi\\Ancika (2024).txt"),
                    6.9,
                    "Benni Setiawan",
                    "/img/poster/Sci-fi/Ancika.Dia.Yang.Bersamaku..jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie93Genres = new ArrayList<>();
            movie93Genres.add(sci_fi);
            movie93.setGenres(movie93Genres);
            movieRepository.save(movie93);

            Movie movie94 = new Movie(
                    "Assassin’s Creed",
                    2016,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Sci-fi\\Assassin’s Creed (2016).txt"),
                    5.7,
                    "Justin Kurzel, Lisa Vick",
                    "/img/poster/Sci-fi/Assassin's Creed.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie94Genres = new ArrayList<>();
            movie94Genres.add(sci_fi);
            movie94.setGenres(movie94Genres);
            movieRepository.save(movie94);

            Movie movie95 = new Movie(
                    "Attack",
                    2024,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Sci-fi\\Attack (2024).txt"),
                    7.3,
                    "Lakshya Raj Anand",
                    "/img/poster/Sci-fi/Attack.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie95Genres = new ArrayList<>();
            movie95Genres.add(sci_fi);
            movie95.setGenres(movie95Genres);
            movieRepository.save(movie95);

            Movie movie96 = new Movie(
                    "Chronicle",
                    2012,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Sci-fi\\Chronicle (2012).txt"),7.0,
                    "Josh Trank, Loma Bennet",
                    "/img/poster/Sci-fi/Chronicle.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie96Genres = new ArrayList<>();
            movie96Genres.add(sci_fi);
            movie96.setGenres(movie96Genres);
            movieRepository.save(movie96);

            Movie movie97 = new Movie(
                    "Mira",
                    2022,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Sci-fi\\Mira (2022).txt"),
                    5.8,
                    "Dmitriy Kiselev",
                    "/img/poster/Sci-fi/Mira.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie97Genres = new ArrayList<>();
            movie97Genres.add(sci_fi);
            movie97.setGenres(movie97Genres);
            movieRepository.save(movie97);

            Movie movie98 = new Movie(
                    "Parallels",
                    2015,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Sci-fi\\Parallels (2015).txt"),
                    6.0,
                    "Christopher Leone",
                    "/img/poster/Sci-fi/Parallel.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie98Genres = new ArrayList<>();
            movie98Genres.add(sci_fi);
            movie98.setGenres(movie98Genres);
            movieRepository.save(movie98);

            Movie movie99 = new Movie(
                    "Seobok",
                    2021,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Sci-fi\\Seobok(2021).txt"),8.3,
                    "Lee Yong-ju",
                    "/img/poster/Sci-fi/Seobok.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie99Genres = new ArrayList<>();
            movie99Genres.add(sci_fi);
            movie99.setGenres(movie99Genres);
            movieRepository.save(movie99);

            Movie movie100 = new Movie(
                    "Source Code",
                    2011,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Sci-fi\\Source Code (2011).txt"),
                    7.5,
                    "Duncan Jones",
                    "/img/poster/Sci-fi/Source code.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie100Genres = new ArrayList<>();
            movie100Genres.add(sci_fi);
            movie100.setGenres(movie100Genres);
            movieRepository.save(movie100);

            Movie movie101 = new Movie(
                    "Space Sweepers",
                    2021,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Sci-fi\\Space Sweepers(2021).txt"),
                    9.3,
                    "Sung-hee Jo",
                    "/img/poster/Sci-fi/Space Sweeperrs.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie101Genres = new ArrayList<>();
            movie101Genres.add(sci_fi);
            movie101.setGenres(movie101Genres);
            movieRepository.save(movie101);

            Movie movie102 = new Movie(
                    "Star Trek Beyond",
                    2016,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Sci-fi\\Star Trek Beyond (2016).txt"),
                    7.1,
                    "Carolina Jiménez, Douglas Plasse",
                    "/img/poster/Sci-fi/Star Trek Beyond.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie102Genres = new ArrayList<>();
            movie102Genres.add(sci_fi);
            movie102.setGenres(movie102Genres);
            movieRepository.save(movie102);

            Movie movie103 = new Movie(
                    "Stargate",
                    1994,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Sci-fi\\Stargate (1994).txt"),
                    7.1,
                    "Kay Sweeney, Roland Emmerich",
                    "/img/poster/Sci-fi/Stargate.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie103Genres = new ArrayList<>();
            movie103Genres.add(sci_fi);
            movie103.setGenres(movie103Genres);
            movieRepository.save(movie103);

            Movie movie104 = new Movie(
                    "The Wandering Earth",
                    2019,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Sci-fi\\The Wandering Earth(2019).txt"),
                    7.5,
                    "Frant Gwo",
                    "/img/poster/Sci-fi/The Wandering Earth (Liu Lang Di Qiu).jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie104Genres = new ArrayList<>();
            movie104Genres.add(sci_fi);
            movie104.setGenres(movie104Genres);
            movieRepository.save(movie104);

            Movie movie105 = new Movie(
                    "The Manchurian Candidate",
                    2004,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Sci-fi\\The Manchurian Candidate(2004).txt"),
                    7.0,
                    "Jonathan Demme",
                    "/img/poster/Sci-fi/The Manchurian Candidate.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie105Genres = new ArrayList<>();
            movie105Genres.add(sci_fi);
            movie105.setGenres(movie105Genres);
            movieRepository.save(movie105);




            Movie movie108 = new Movie(
                    "The God of Cookery",
                    1996,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Chow\\The God of Cookery (1996).txt"),
                    7.3,
                    "Lik-Chi Lee, Stephen Chow",
                    "/img/poster/Stephen chow/God of Cookery.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie108Genres = new ArrayList<>();
            movie108Genres.add(stephenChow);
            movie108.setGenres(movie108Genres);
            movieRepository.save(movie108);

            Movie movie109 = new Movie(
                    "Look Out, Officer!",
                    1990,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Chow\\Look Out, Officer! (1990).txt"),
                    6.1,
                    "Lau Sze-Yu",
                    "/img/poster/Stephen chow/Look Out,Officer!.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie109Genres = new ArrayList<>();
            movie109Genres.add(stephenChow);
            movie109.setGenres(movie109Genres);
            movieRepository.save(movie109);

            Movie movie110 = new Movie(
                    "The Lucky Guy",
                    1998,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Chow\\The Lucky Guy.txt"),
                    6.1,
                    "Lee Lik-Chi",
                    "/img/poster/Stephen chow/The Lucky Guy.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie110Genres = new ArrayList<>();
            movie110Genres.add(stephenChow);
            movie110.setGenres(movie110Genres);
            movieRepository.save(movie110);

            Movie movie111 = new Movie(
                    "From Beijing with Love",
                    1994,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Chow\\From Beijing with Love (1994).txt"),
                    7.2,
                    "Stephen Chow,Lik-Chi Lee",
                    "/img/poster/Stephen chow/From Beijng with Love.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie111Genres = new ArrayList<>();
            movie111Genres.add(stephenChow);
            movie111.setGenres(movie111Genres);
            movieRepository.save(movie111);

            Movie movie112 = new Movie(
                    "King of Comedy",
                    1999,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Chow\\King of Comedy (1999).txt"),
                    7.3,
                    "Stephen Chow, Lik-Chi Lee",
                    "/img/poster/Stephen chow/King of Comedy.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie112Genres = new ArrayList<>();
            movie112Genres.add(stephenChow);
            movie112.setGenres(movie112Genres);
            movieRepository.save(movie112);

            Movie movie113 = new Movie(
                    "Loving Vincent",
                    2017,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Chow\\Loving Vincent (2017).txt"),
                    7.8,
                    "Dorota Kobiela, Hugh Welchman",
                    "/img/poster/Vincent/Loving Vincent.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie113Genres = new ArrayList<>();
            movie113Genres.add(vincent);
            movie113.setGenres(movie113Genres);
            movieRepository.save(movie113);

            Movie movie114 = new Movie(
                    "Van Gogh: Painted with Words",
                    2010,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Chow\\Van Gogh Painted with Words (2010).txt"),
                    8.1,
                    "Andrew Hutton",
                    "/img/poster/Vincent/Van Gogh.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie114Genres = new ArrayList<>();
            movie114Genres.add(vincent);
            movie114.setGenres(movie114Genres);
            movieRepository.save(movie114);

            Movie movie115 = new Movie(
                    "Vincent & Theo",
                    1990,
                    longString("D:\\MovieNet\\src\\main\\resources\\static\\text\\plot\\Chow\\Vincent & Theo (1990).txt"),
                    7.0,
                    "Robert Altman",
                    "/img/poster/Vincent/Vincent & Theo.jpg",
                    "D:\\MovieNet\\src\\main\\resources\\static\\file\\movie\\Action\\(CM) Hancock.2008.Unrated.BluRay.Remux.1080p.AVC.AAC5.1 - Naung.mp4");

            //Adding Genre
            List<Genre> movie115Genres = new ArrayList<>();
            movie115Genres.add(vincent);
            movie115.setGenres(movie115Genres);
            movieRepository.save(movie115);




            // Initialize users
            User user1 = new User(
                    "Swan Htet",
                    "swanh@gmail.com",
                    passwordEncoder.encode("admin123"),
                    role2);
            userRepository.save(user1);

            User user2 = new User(
                    "Swan Soe",
                    "swanh@gmail.com",
                    passwordEncoder.encode("admin123"),
                    role2);
            userRepository.save(user2);

            User user3 = new User(
                    "Htet Htet",
                    "htethtet@gmail.com",
                    passwordEncoder.encode("admin123"),
                    role2);
            userRepository.save(user3);

            User user4 = new User(
                    "Min Htet",
                    "minhtet@gmail.com",
                    passwordEncoder.encode("admin123"),
                    role2);
            userRepository.save(user4);

            User user5 = new User(
                    "Naing Naing",
                    "naingnaing@gmail.com",
                    passwordEncoder.encode("admin123"),
                    role2);
            userRepository.save(user5);


            // Initialize reviews
            List<Movie> movieList = movieRepository.findAll();
            for(int i =0; i< movieList.size(); i++){
                Movie movie = movieList.get(i);
                Review review1 = new Review(
                        movie,
                        user1,
                        5,
                        "Very Good",
                        LocalDate.now() );
                reviewRepository.save(review1);

                Review review2 = new Review(
                        movie,
                        user2,
                        5,
                        """
                                Emotionally profound and masterfully directed.
                                a must-watch for any movie lover""",
                        LocalDate.now() );
                reviewRepository.save(review2);

                Review review3 = new Review(
                        movie,
                        user3,
                        5,
                        "An inspiring and heartwarming masterpiece",
                        LocalDate.now() );
                reviewRepository.save(review3);

                Review review4 = new Review(
                        movie,
                        user4,
                        5,
                        """
                                With its compelling plot and brilliant acting,
                                stands as one of the greatest films ever made""",
                        LocalDate.now() );
                reviewRepository.save(review4);

                Review review5 = new Review(
                        movie,
                        user5,
                        5,
                        """
                                an emotional journey that leaves a lasting impact.
                                A beautifully told story""",
                        LocalDate.now() );
                reviewRepository.save(review5);


            }

        }
    }

    String longString(String str){
        try {
            return Files.readString(Paths.get(str));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


