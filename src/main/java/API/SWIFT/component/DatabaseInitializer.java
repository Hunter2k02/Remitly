package API.SWIFT.component;

import API.SWIFT.dto.CSVDTO;

import API.SWIFT.model.Bank;
import API.SWIFT.repository.BankRepository;
import API.SWIFT.service.DataImportService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.ArrayList;


@Component
public class DatabaseInitializer implements CommandLineRunner {
    private final BankRepository bankRepository;

    public DatabaseInitializer(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if(bankRepository.count() == 0) {
            //Loading data from csv file
            ArrayList<CSVDTO> dataList =  DataImportService.readData("src/main/resources/SWIFT.csv");
            for(CSVDTO csvDto : dataList ) {
                bankRepository.save(new Bank(csvDto.getCountryISO2(), csvDto.getSwiftCode(),
                        csvDto.getCodeType(), csvDto.getBankName(), csvDto.getAddress(), csvDto.getTownName(),
                        csvDto.getTimeZone(), csvDto.getCountryName(), csvDto.isHeadquarter())
                );
            }
        }
    }
}
