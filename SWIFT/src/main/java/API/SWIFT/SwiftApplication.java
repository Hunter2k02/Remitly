package API.SWIFT;

import API.SWIFT.dto.CSVDto;
import API.SWIFT.model.Branch;
import API.SWIFT.repository.BranchRepository;
import API.SWIFT.repository.HeadquarterRepository;
import API.SWIFT.service.DataImportService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class SwiftApplication {
	public static void main(String[] args) {
		SpringApplication.run(SwiftApplication.class, args);
	}

	@Bean CommandLineRunner run(){
		return args -> {
				ArrayList<CSVDto> dataList =  DataImportService.readData("src/main/resources/SWIFT.csv");
				for(CSVDto csvDto : dataList){
					System.out.println(csvDto
					);
				}
		};
	}
}
