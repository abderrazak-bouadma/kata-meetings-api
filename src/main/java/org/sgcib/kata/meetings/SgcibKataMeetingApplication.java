package org.sgcib.kata.meetings;

import com.fasterxml.classmate.TypeResolver;
import org.sgcib.kata.meetings.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.schema.WildcardType;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.*;
import java.util.Date;

import static com.google.common.collect.Lists.newArrayList;
import static springfox.documentation.schema.AlternateTypeRules.newRule;

@SpringBootApplication
@EnableCaching
@EnableSwagger2
public class SgcibKataMeetingApplication implements CommandLineRunner {

    private final UserRepository userRepository;
    private final MeetingRoomRepository meetingRoomRepository;
    private final MeetingRepository meetingRepository;
    private final TypeResolver typeResolver;

    @Autowired
    public SgcibKataMeetingApplication(UserRepository userRepository, MeetingRoomRepository meetingRoomRepository, MeetingRepository meetingRepository, TypeResolver typeResolver) {
        this.userRepository = userRepository;
        this.meetingRoomRepository = meetingRoomRepository;
        this.meetingRepository = meetingRepository;
        this.typeResolver = typeResolver;
    }

    public static void main(String[] args) {
        SpringApplication.run(SgcibKataMeetingApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {

        // some users
        User mario = userRepository.save(new User("mario@apiland.com"));
        User yoshi = userRepository.save(new User("yoshi@apiland.com"));
        User luigi = userRepository.save(new User("luigi@apiland.com"));

        // some meeting rooms
        MeetingRoom pacMan = meetingRoomRepository.save(new MeetingRoom("PacMan", 4));
        meetingRoomRepository.save(new MeetingRoom("Donkey Kong", 7));
        MeetingRoom marioBros = meetingRoomRepository.save(new MeetingRoom("Mario Bros.", 8));
        meetingRoomRepository.save(new MeetingRoom("Super Mario Bros.", 12));
        meetingRoomRepository.save(new MeetingRoom("Tetris", 12));
        meetingRoomRepository.save(new MeetingRoom("Space Invaders", 18));
        meetingRoomRepository.save(new MeetingRoom("Galaga", 2));
        MeetingRoom zelda = meetingRoomRepository.save(new MeetingRoom("Zelda", 4));
        meetingRoomRepository.save(new MeetingRoom("Pole Position", 4));
        meetingRoomRepository.save(new MeetingRoom("Paper Boy", 6));

        // some meetings
        LocalDateTime ldt = LocalDateTime.of(2016, Month.NOVEMBER, 30, 14, 0, 0, 0);
        ZonedDateTime zdt = ldt.atZone(ZoneId.of("UTC"));
        Date aDay = Date.from(zdt.toInstant());
        meetingRepository.save(new Meeting(mario, marioBros, aDay, 2));

        ldt = LocalDateTime.of(2016, Month.DECEMBER, 6, 14, 0);
        zdt = ldt.atZone(ZoneId.of("UTC"));
        aDay = Date.from(zdt.toInstant());
        meetingRepository.save(new Meeting(luigi, pacMan, aDay, 2));
    }

    @Bean
    public Docket petApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/")
                .directModelSubstitute(LocalDate.class,
                        String.class)
                .genericModelSubstitutes(ResponseEntity.class)
                .alternateTypeRules(
                        newRule(typeResolver.resolve(DeferredResult.class,
                                typeResolver.resolve(ResponseEntity.class, WildcardType.class)),
                                typeResolver.resolve(WildcardType.class)))
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET,
                        newArrayList(new ResponseMessageBuilder()
                                .code(500)
                                .message("500 message")
                                .responseModel(new ModelRef("Error"))
                                .build()))
                .securitySchemes(newArrayList(apiKey()))
                .enableUrlTemplating(true)
                .globalOperationParameters(
                        newArrayList(new ParameterBuilder()
                                .name("someGlobalParameter")
                                .description("Description of someGlobalParameter")
                                .modelRef(new ModelRef("string"))
                                .parameterType("query")
                                .required(true)
                                .build()))
                .tags(new Tag("Pet Service", "All apis relating to pets"));
    }

    private ApiKey apiKey() {
        return new ApiKey("mykey", "api_key", "header");
    }
}
