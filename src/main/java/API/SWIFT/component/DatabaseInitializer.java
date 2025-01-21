package API.SWIFT.component;

import API.SWIFT.dto.CSVDto;

import API.SWIFT.model.Bank;
import API.SWIFT.repository.BankRepository;
import API.SWIFT.service.DataImportService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.ArrayList;


@Component
public class DatabaseInitializer implements CommandLineRunner {
   private BankRepository bankRepository;

    public DatabaseInitializer(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        ArrayList<CSVDto> dataList =  DataImportService.readData("src/main/resources/SWIFT.csv");
        for (CSVDto csvDto : dataList) {
            System.out.println(csvDto);
        }
        if(bankRepository.count() == 0) {
            System.out.println("Tables are empty, starting table population...");
            //Loading data from csv file
            //ArrayList<CSVDto> dataList =  DataImportService.readData("src/main/resources/SWIFT.csv");
            for(CSVDto csvDto : dataList ) {
                bankRepository.save(new Bank(csvDto.getAddress(), csvDto.getBankName(),
                        csvDto.getCountryISO2(), csvDto.getCountryName(),
                        csvDto.getSwiftCode(), csvDto.isHeadquarter()));
            }
        }
    }
}
