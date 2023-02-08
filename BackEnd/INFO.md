## Monolithic

1. @RequestBody를 String으로 받으면 Json처리 안됨.
2. ResposneDTO에 Entity 들어가면 프록시 객체일 수도 있으므로 왠만하면 안넣는게 좋을듯함.
3. INNER JOIN, LEFT OUTER JOIN, RIGHT OUTER JOIN이 존재하고, 일대다 상태에서 INNER JOIN을 해도 문제없음. 데이터 중복은 LEFT JOIN에서 발생할 확률이 크므로 조심.
4. 일대다 상태에서 FETCH JOIN을 하면 무조건, 뻥튀기 문제가 발생하게 됨. 따라서 DISTINCT가 요구됨.
5. OneToOne에서 주인이 아닌 쪽은 LazyLoading이 동작하지 않는데, OneToMany에서 주인이 아닌 쪽에서는 제대로 동작함.
6. 연관관계 Entity를 가지고 있을 때 주인이라면 DB상에서 FK를 가지고 있는 꼴임. 근데, JPA상에서는 객체이므로 Lazy Loading을 하면 프록시 객체를 가지게 되는데, 해당 프록시 객체에서 Id는 Getter로 조회해도 프록시 초기화가 발생하지 않음. 당연하게 DB상에서는 FK를 가지고 있으니까 그러는 듯함.
7. 프론트에 넘겨줄 DTO에 프록시 객체가 껴있지 않도록 주의해야 함. 아니면 프록시 객체 출력 예외 발생함.
8. 양방향 연관관계시 프론트에 json으로 넘겨주려면 한쪽에 @JsonIgnore을 붙여줘야 함.
9. Hibernate5Module를 등록해주면 프록시 객체를 json으로 변환시 예외가 발생하지 않고, 초기화되지 않은 프록시 객체는 출력되지 않고, 초기화된 프록시 객체만 출력됨. 다만, 이렇게 Entity를 직접 노출시키는 것보다 DTO로 만들어주는게 더 바람직함.
10. DB에서 search 속도 최적화를 위해 사용되는 INDEX는 search를 원하는 특정 칼럼에 대해 INDEX만 생성하면 이후의 조회에 적용됨. INDEX 사용시 explain을 해서 INDEX를 타는지 확인해주는 게 좋음.
11. 로그인, 검색 부분에서 INDEX를 사용해주는 게 좋을 듯함.
12. Entity타입 Collection은 왠만하면 다대다 관계일 것이므로 중간에 다대다 전용 테이블을 설정하고, 값타입 Collection은 값타입을 Entity로 묶어서 따로 테이블로 만들어주는 게 좋음.
13. @EmbeddedId와 @MapsId의 조합으로 외래키1개+일반컬럼1개의 복합키를 만들 수 있음.
14. Entity의 속성값들은 전부 값타입으로 줘야 함.
15. mappedBy를 사용해 양방향 연관관계를 설정하는 이유는 다른 테이블과 join한 결과를 해당 Entity로 가져올 때 다른 테이블의 컬럼값들을 담아오기 위해서임. 그렇게 안하면 join한 테이블들의 컬럼값들을 가져올 수 없음.
16. 가져오는 Entity 내의 연관관계 Entity 내의 연관관계 Entity를 프록시 객체로 가져오지 않으려면 Fetch join을 하나의 쿼리안에 여러번 실행해서 해결할 수 있음.
17. 팔로우한 유저의 일지 중 가장 최신 날짜를 조회한다던가, 작물id로 조회된 일지들 중 가장 최신 날짜를 조회하는 쿼리문은 서브쿼리를 사용해줘야 함. 근데 jpql에서 서브쿼리 사용하는거 빡세서 querydsl공부하고 해야할거 같음.
18. repository의 save 메서드를 통해 반환되는 Entity는 연관관계 Entity들이 전부 진짜로 채워져서 나옴. find는 프록시 객체로 나오는 것과 다름.
19. entity의 dirty check를 통해 변경시키는 게 update 쿼리를 짜는것보다 간단할 듯함.

## MSA

1.  FeignClient에서 RequestDTO를 사용하려면 사용하려는 곳에서 요청하려는 곳의 RequestDTO의 클래스를 가지고 있어야 함. 예를 들어 Member에서 Order의 메서드를 FeignClient로 실행하려면 OrderRequestDTO를 가지고 있어야 함. 이는 실무에서 라이브러리를 공유함으로써 해결할 수 있음.
2.  Swagger API DOCS를 보면서 Postman으로 데이터를 보내주는 게 엄청 편함.
3.  FeignClient를 사용하는 곳은 Service, Controller 모두 가능할 듯함.
4.  MSA 구조를 택한 대신, JPA의 객체지향적 이점을 포기해야 함. 예전에는 Diary만 조회해도 안에 User를 값으로 가지고 있기 때문에 자동으로 Join 처리되서
    User데이터도 채워졌는데, MSA는 Diary와 User가 독립적으로 조회가 되므로, 연관관계 처리가 불가능해 SQL을 두번날려야 함. 따라서 DTO를 만들때도 UserDTO를 따로 가져와서
    DiaryDTO에 채워주는 방식을 사용해야함.
5.  DTO에 Entity의 enum 타입이 들어갈 때, String으로 처리해주는 게 좋을 듯 함. 그렇게 안하면, 해당 DTO를 필요로 하는 다른 MSA의 FeignClient 쪽에서 해당 enum 클래스를 가지고 있어야 함.
6.  Controller의 메서드 중 create, update는 response로 id만 넘겨주고 id를 넘겨받은 프론트에서 해당 id로 데이터를 다시 조회할 수 있도록 하는게 바람직함. search는 데이터가 들어있는 DTO를 반환해주는 게 바람직함.
7.  알람이나 태그파싱은 Controller말고 Service에서 해주는게 Controller가 깔끔해질 듯함.
8.  다른 MSA에서 가져와야 알 수 있는 DTO를 내부 값으로 가지는 DTO를 만들기 위해서는 다른 MSA에서 가져온 DTO를 생성자 매개변수로 가지는 DTO로 설계해주는 게 좋을 듯함. 그래야 FeignClient의 사용처를 Controller로 당길 수 있음.
9.  busrefresh 사용시, hikariCP 관련 문제 발생하면 아래의 내용을 추가함.
    ```
    spring:
      cloud:
        refresh:
          extra-refreshable: com.zaxxer.hikari.HikariDataSource
    ```
10. circuitbreaker는 에러가 자주 발생하는 부분에서만 사용하는 게 좋을 듯함.
11. 모니터링 기술로 zipkin, grafana를 사용함.
12. Zipkin을 추가적을 사용할 시, Command line is too long 에러가 뜨는데 이를 해결하기 위해 idea dir의 workspace.xml 파일의 PropertiesComponent 컴포넌트에 `"dynamic.classpath": "true"` 를 추가해줌.
13. config server를 제외한 나머지 service들에 zipkin과 sleuth 설정을 해줬음.
14. grafana를 사용하려는데, prometheus를 docker로 실행할 때, yml파일을 변경해줘야 하므로 까다로워서 micrometer 의존성만 config server를 제외한 나머지 service들에 추가해줌.
15. Zipkin을 사용하면 sql 로그가 콘솔에 찍히지 않음. 따라서 개발 과정에서 Zipkin을 사용하는 것보다 개발 다하고 모니터링으로 사용하는 게 좋을 듯함.
16. 카프카를 통한 CQRS 처리를 위해 Update 쿼리들은 다 message queue로 감. 근데 jpa에서 key 자동생성전략을 통해 DB에 저장되는 pk값을 설정할 수 있었는데, 카프카는 connector를 통해 topic에 들어온 값 그대로 db에 업데이트 해버리니까 key 자동생성전략을 못쓰고 null이 들어가버림. 이를 해결하기 위해 애초에 자바에서 쿼리를 날릴 때 id값을 난수로 채워주고 message queue에 보내버려야 함. 진짜 제대로 할려면 streams를 활용해 id 값에 난수를 넣어주는 stream처리를 해볼 수도 있겠음.
17. KAFKA를 사용하는 이유는 CQRS와 데이터 동기화를 위해서고, 실제로는 messeage queue에서 DB로 값을 넣기 전에 적합성 검사를 하고 넣어줘야 하지만, 여기서는 간단하게 connector로 바로 넣어줬음. kafka는 단순히 db에 데이터를 입력해주는 역할 뿐 아니라, MSA간에 서로의 Entity에 대한 Update 요청을 할 때 사용해주는 것도 있음.
18. 8083 port는 KafkaConnector가 사용중이라 변경해줘야 함.
19. 현재 토픽의 삭제 정책을 1분으로 해놔서 나중에 배포할 때 수정해줘야 함.
20. kafka에서 connector가 db에 저장할 때 id가 null값이라도 JPA가 애플리케이션 시작할 때 ddl로 만들어주는 테이블 설정때문에 자동난수 설정 가능할지도...??
21. Config server의 basedir은 사용자마다 달라질 수 있어서 조심해야함.
22. config -> discovery -> gateway -> notify -> user -> plant -> chat -> board -> calendar 순으로 실행해야 함.
23. MSA 배포과정에서 아래 오류가 발생할 수 있음.
    ```
      Error running MaryfarmUserServiceApplication. Command line is too long. Shorten the command line via JAR manifest or via a classpath file and rerun.
    ```
24. LocalDateTime.now()는 UTC 기준이므로 한국시간 -9임. 따라서 ZonedDateTime.now()를 대신 사용하고, Data JPA의 Auditing 기능도 UTC이므로, prePersist()를 대신 사용함.
25. LocalDateTime을 RequestBody로 받기 위해서는 RequestDTO에서 @DateTimeFormat()을 명시해줘야함.
26. FeignClient로는 상대 MSA에서 Query를 가져오기 위해서만 사용하고, 상대 MSA의 Command를 유도하고 싶다면, kafka topic에 값을 넣어서
    상대 MSA에서 listen할 수 있도록 해줘야함.
27. LocalDateTime을 String으로 직렬화하기 위해서는 Custom Serializer, Deserializer를 구현해줘야 함.

    ```
    public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {
        private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");

        @Override
        public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            return LocalDateTime.parse(jsonParser.getText(), DATE_FORMAT);
        }
    }
    ```

    ```
    public class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {
        private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");
        @Override
        public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            gen.writeString(value.format(DATE_FORMAT));
        }
    }
    ```

28. 현재 Event Driven Architecture가 적용된 부분은 Notify가 Follow, Diary 생성을 마킹임.
29. springboot 최신버전부터는 application-{profile}.yml의 분리는 `spring.config.use-legacy-processing=true` 를 붙여야 가능해짐.
30. redis를 사용하려면 starter-redis, cache 라이브러리를 추가하고, `@EnableCaching`를 main()에 붙여주고, yml에 redis 환경설정을 추가해주고, RedisConfig 클래스를 생성해줘야함.
31. `@EnableScheduling`를 main()에 붙여주면, SpringBoot내에서 cron scheduling 기능을 이용할 수 있음.
32. devTools 때문에 캐시에 있던 데이터를 가져오던 과정에서 ClassCastException이 발생함. 이는 `.defaultCacheConfig(Thread.currentThread().getContextClassLoader())` 로 설정변경을 해서 해결함.
33. 게시판 전체 데이터를 캐시로 활용할려 했는데, 누가 글을 쓰면 유동적으로 변해야 하는 데이터가 캐시에서는 변하지 않고 유지되고 있기 때문에 캐시로 사용하기에는 바람직하지 않은 것 같음.


34. build est를 위한 스크립트!! 빌드 가즈아~~~!~!!! 