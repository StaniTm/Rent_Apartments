package com.example.myproject.service.business;


import com.example.myproject.domain.entity.CityEntity;
import com.example.myproject.domain.enums.City;
import com.example.myproject.domain.model.errors.CityNotFoundException;
import com.example.myproject.domain.model.view.CityViewModel;
import com.example.myproject.repository.CityRepository;
import com.example.myproject.service.initDB.InitDB;
import com.example.myproject.utils.Utils;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService implements InitDB {
    private final CityRepository cityRepository;
    private final ModelMapper modelMapper;
    private final Utils utils;

    @Autowired
    public CityService(CityRepository cityRepository, ModelMapper modelMapper, Utils utils) {
        this.cityRepository = cityRepository;
        this.modelMapper = modelMapper;
        this.utils = utils;
    }

    public List<CityViewModel> getAllCityViews() {
        return this.cityRepository.
                findAll().
                stream().
                map(cityEntity -> this.modelMapper.map(cityEntity, CityViewModel.class)).
                toList();
    }

    public CityEntity getCityByName(String name) {
        City city = null;
        switch (name) {
            case "Sunny Beach":
                city = City.SUNNY_BEACH;
                break;
            case "Plovdiv":
                city = City.PLOVDIV;
                break;
            case "Sofia":
                city = City.SOFIA;
                break;
        }
        if (city == null) throw new CityNotFoundException("No City with name " + name + "!");
        return this.cityRepository.findByName(city).orElseThrow();
    }

    public CityViewModel getCityView(String name) {
        CityEntity destination = this.getCityByName(name);
        return this.modelMapper.
                map(this.cityRepository.
                        findByName(destination.
                                getName()).get(), CityViewModel.class).setDotIndex(destination.getDescription().indexOf('.'));

    }




    //Initialize Destinations
    @Override
    public boolean isDbInit() {
        return this.cityRepository.count() == 0;
    }

    @Override
    @PostConstruct
    public void dbInit() {
        if (isDbInit()) {
            this.createSunnyBeach();
            this.createPlovdiv();
            this.createrSofia();
        }
    }

    private void createSunnyBeach() {
        this.cityRepository.saveAndFlush(new CityEntity().
                setDescription("Sunny Beach is the most modern and cosmopolitan resort on the Bulgarian Black Sea coast, located about 35 km north of Burgas.          It is located in the southern part of the Black Sea coast, in a spacious and picturesque bay. Its beach is 8 km long and in places is up to 100 meters wide. Hotels offer every opportunity for a relaxing holiday - indoor and outdoor pools, green parks and gardens," +
                        " sports facilities, restaurants, day and night bars," +
                        " coffeehouses, beauty salons, spas and more. ").
                setName(City.SUNNY_BEACH));
    }

    private void createPlovdiv() {
        this.cityRepository.saveAndFlush(new CityEntity().
                setDescription("Plovdiv, second largest city of Bulgaria, situated in the south-central part of the country. It lies along the Maritsa River and is situated amid six hills that rise from the Thracian Plain to a height of 400 feet (120 metres). Called Pulpudeva in Thracian times, it was renamed Philippopolis in 341 bc after its conquest by Philip II of Macedonia. From ad 46 it was called Trimontium and was the capital of the Roman province of Thrace. Plovdiv repeatedly changed hands during the Middle Ages until 1364, when it was taken by the Turks, who called it Philibé. After the Russo-Turkish War (1877–78), it became capital of Turkish Eastern Rumelia, which united with Bulgaria in 1885. It officially assumed its present name after World War I.").
                setName(City.PLOVDIV));
    }

    private void createrSofia() {
        this.cityRepository.saveAndFlush(new CityEntity().
                setDescription("Sofia, Bulgarian Sofiya, capital of Bulgaria. It is situated near the geographical centre of the Balkans region, in the Sofia Basin, a troughlike valley in the western part of the country. The Serdi (Sardi), a Thracian tribe, established a settlement in the region in the 8th century bce. This community was conquered soon after 29 bce by the Romans, who named it Serdica (Greek: Sardica). It flourished during the reign of the emperor Trajan (98–117) and reached its greatest height under the emperor Constantine I the Great; in 342 or 343 it was the site of an important meeting of Christian bishops, the Council of Sardica. From the 4th century it was part of the Western Roman Empire, but with the decline of Rome passed to Byzantium; it was plundered by Attila and the Huns in 441–447. During the 6th century Byzantine influence increased under the emperor Justinian, and the restored Church of St. Sofia, which later gave the town its name, survives from this period. In 809 the Bulgarian khan Krum seized the town and incorporated it in the Bulgarian state; it was given the Slav name Sredets (Greek: Triaditsa). It was under Byzantine rule from 1018 until 1185, when the second Bulgarian Empire was established.").
                setName(City.SOFIA));
    }

}

