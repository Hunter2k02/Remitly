package API.SWIFT.service;

import API.SWIFT.dto.CSVDTO;
import org.springframework.stereotype.Service;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

@Service
public class DataImportService {
    public static ArrayList<CSVDTO> readData(String filename) {
        ArrayList<CSVDTO> dataList = new ArrayList<>();

        File file = new File(filename);
        try(Scanner fileScanner = new Scanner(file)){
            ArrayList<String> lines = new ArrayList<>();
            while(fileScanner.hasNextLine()){
                lines.add(fileScanner.nextLine());
            }
            for(int i = 1; i < lines.size(); i++){
                CSVDTO csvDto = getCsvDto(lines, i);
                dataList.add(csvDto);
            }
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return dataList;
    }

    private static CSVDTO getCsvDto(ArrayList<String> lines, int i) {
        String line = lines.get(i);
        String[] data = line.split(";");
        String countryISO2 = data[0];
        String swiftCode = data[1];
        String codeType = data[2];
        String bankName = data[3];
        String address = data[4];
        String townName = data[5];
        String countryName = data[6];
        String timeZone = data[7];
        boolean isHeadquarter = swiftCode.endsWith("XXX");

        return new CSVDTO(countryISO2, swiftCode, codeType, bankName, address, townName, countryName, timeZone, isHeadquarter);
    }


}
