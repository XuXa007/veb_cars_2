package org.example.init;

import org.example.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class DataInitializer implements CommandLineRunner {
    @Autowired
    private RoleService roleService;
    @Autowired
    private BrandService brandService;
    @Autowired
    private UsersService usersService;
    @Autowired
    private ModelService modelService;
    @Autowired
    private OfferService offerService;


    @Override
    public void run(String... args) throws Exception {
        seedData();
    }

    private void seedData() throws IOException {
//        LocalDateTime now = LocalDateTime.now();
//        LocalDateTime yesterday = LocalDateTime.now().minusDays(1);
//
//        String imagePathBMW = "src/main/resources/image/BMW_i8.jpg";
//
//        BigDecimal price1 = new BigDecimal(14000000);
//        BigDecimal price2 = new BigDecimal(90000000);
//
//        String uuid1 = String.valueOf(UUID.randomUUID());
//        String uuid2 = String.valueOf(UUID.randomUUID());
//
//        BrandDto brand1 = new BrandDto(uuid1, "BWV", now, yesterday);
//        BrandDto brand2 = new BrandDto(uuid2, "Mazda", now, yesterday);
//
//        brand1 = brandService.registerBrand(brand1);
//        brand2 = brandService.registerBrand(brand2);
//
//        String uuid3 = String.valueOf(UUID.randomUUID());
//        String uuid4 = String.valueOf(UUID.randomUUID());
//
//        RoleDto roles1 = new RoleDto(uuid3, RoleEnum.User);
//        RoleDto roles2 = new RoleDto(uuid4, RoleEnum.Admin);
//
//        roles1 = roleService.registerRole(roles1);
//        roles2 = roleService.registerRole(roles2);
//
//        String uuid5 = String.valueOf(UUID.randomUUID());
//        String uuid6 = String.valueOf(UUID.randomUUID());
//
//        UsersDto user1 = new UsersDto(uuid5,roles1, "userJohn", "passUser", "John",
//                "Pack", true, "urlImage_1", now, yesterday);
//
//        UsersDto user2 = new UsersDto(uuid6, roles2, "adminTifa", "passAdmin", "Tifa",
//                "Sharikova", true, "urlImage_2", now, yesterday);
//
//        user1 = usersService.registerUser(user1);
//        user2 = usersService.registerUser(user2);
//
//        String uuid7 = String.valueOf(UUID.randomUUID());
//        String uuid8 = String.valueOf(UUID.randomUUID());
//
//
//        ModelDto model1 = new ModelDto(uuid7, brand2,"CX-5", Category.Car, "image_url_1", 2001, 2023, now, yesterday);
//        ModelDto model2 = new ModelDto(uuid8, brand1, "I-8", Category.Bus, "image_url_2", 2020, 2030, now, yesterday);
//
//        model1 = modelService.registerModel(model1);
//        model2 = modelService.registerModel(model2);
//
//        String uuid9 = String.valueOf(UUID.randomUUID());
//        String uuid10 = String.valueOf(UUID.randomUUID());
//
//        OfferDto offer1 = new OfferDto(uuid9, model1,user1,"Компактный кроссовер, выпускаемый японской автомобильной компанией Mazda. Первый серийный автомобиль Mazda, дизайн кузова которого создан согласно идеологии KODO - Дух движения. ",
//                Engine.Electric, imagePathBMW, 1200, price1, Transmission.Automatic, 2009,
//                now, yesterday);
//
//        OfferDto offer2 = new OfferDto(uuid10, model2, user2,"Автомобиль компании BMW.\n" +
//                "Он представлен в кузове двухдверного купе и выполнен из алюминия и поликарбоната. Коэффициент аэродинамического сопротивления составляет 0,26.",
//                Engine.Electric, "image_url_2", 9000, price2, Transmission.Automatic, 2020,
//                now, yesterday);
//
//        offer1 = offerService.registerOffer(offer1);
//        offer2 = offerService.registerOffer(offer2);

//        List<ModelDto> modelsByBrand = modelService.findModelByBrandName("Mazda");
//        for (ModelDto model : modelsByBrand) {
//            System.out.println(model);
//        }

//        System.out.println(brand1);
//        System.out.println(brand2);
//
//        System.out.println(roles1);
//        System.out.println(roles2);
//
//        System.out.println(user1);
//        System.out.println(user2);
//
//        System.out.println(model1);
//        System.out.println(model2);
//
//        System.out.println(offer1);
//        System.out.println(offer2);

//        System.out.println("after del role");

//        brandService.deleteBrand(brand1.getId());

//        modelService.deleteModel(model1.getId());

//        offerService.deleteOffer(offer1.getId());



//        System.out.println(brand1);
//        System.out.println(brand2);
//
//        System.out.println(roles1);
//        System.out.println(roles2);
//
//        System.out.println(user1);
//        System.out.println(user2);
//
//        System.out.println(model1);
//        System.out.println(model2);
//
//        System.out.println(offer1);
//        System.out.println(offer2);


    }
}
