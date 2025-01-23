package API.SWIFT.service;

import API.SWIFT.dto.*;
import API.SWIFT.model.Bank;
import API.SWIFT.repository.BankRepository;
import org.aspectj.bridge.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BankService {
    private final BankRepository bankRepository;
    public BankService(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }
    public ResponseEntity<?> getBankBySwiftCode(String swiftCode) {
        Bank bank = bankRepository.findBySwiftCode(swiftCode);

        if (bank == null) {
            System.out.println("Bank not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no bank with swift code " + swiftCode);
        } else if (bank.isHeadquarter()) {
            HeadquarterResponseDTO headquarterDto = new HeadquarterResponseDTO();

            List<Bank> branches = bankRepository.findAllBranches(swiftCode.substring(0, swiftCode.length() - 3));
            List<BranchResponseDTO> branchResponseDtos = getBranchResponseDTOS(branches);

            headquarterDto.setAddress(bank.getAddress());
            headquarterDto.setBankName(bank.getBankName());
            headquarterDto.setCountryISO2(bank.getCountryISO2());
            headquarterDto.setCountryName(bank.getCountryName());
            headquarterDto.setSwiftCode(swiftCode);
            headquarterDto.setBranches(branchResponseDtos);
            return ResponseEntity.ok(headquarterDto);
        }
        else{
            BranchResponseDTO branchResponseDto = new BranchResponseDTO();
            branchResponseDto.setAddress(bank.getAddress());
            branchResponseDto.setBankName(bank.getBankName());
            branchResponseDto.setCountryName(bank.getCountryName());
            branchResponseDto.setCountryISO2(bank.getCountryISO2());
            branchResponseDto.setSwiftCode(swiftCode);
            return ResponseEntity.ok(branchResponseDto);
        }


    }

    private static List<BranchResponseDTO> getBranchResponseDTOS(List<Bank> branches) {
        List<BranchResponseDTO> branchResponseDtos = new ArrayList<>();
        for (Bank b : branches) {
            BranchResponseDTO branchResponseDto = new BranchResponseDTO();
            branchResponseDto.setAddress(b.getAddress());
            branchResponseDto.setBankName(b.getBankName());
            branchResponseDto.setCountryName(b.getCountryName());
            branchResponseDto.setCountryISO2(b.getCountryISO2());
            branchResponseDto.setSwiftCode(b.getSwiftCode());
            branchResponseDtos.add(branchResponseDto);
        }
        return branchResponseDtos;
    }

    public ResponseEntity<?> getBanksByCountryISO2code(String countryISO2code) {
        List<Bank> banks = bankRepository.findAllByCountryISO2(countryISO2code);

        if (banks.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There are no SWIFT codes with given countryISO2 code.");
        }
        else{
            List<SwiftCodeResponseDTO> swiftCodeResponseDTOS = new ArrayList<>();
            CountrySwiftCodesResponseDTO countrySwiftCodesResponseDTO = new CountrySwiftCodesResponseDTO();
            for(Bank b : banks){
                SwiftCodeResponseDTO swiftCodeResponseDto = new SwiftCodeResponseDTO();
                swiftCodeResponseDto.setAddress(b.getAddress());
                swiftCodeResponseDto.setBankName(b.getBankName());
                swiftCodeResponseDto.setCountryISO2(b.getCountryISO2());
                swiftCodeResponseDto.setHeadquarter(true);
                swiftCodeResponseDto.setSwiftCode(b.getSwiftCode());
                swiftCodeResponseDTOS.add(swiftCodeResponseDto);
            }
            countrySwiftCodesResponseDTO.setCountryISO2(countryISO2code);
            countrySwiftCodesResponseDTO.setCountryName(banks.get(0).getCountryName());
            countrySwiftCodesResponseDTO.setSwiftCodes(swiftCodeResponseDTOS);
            return ResponseEntity.ok(countrySwiftCodesResponseDTO);
        }
    }
    public ResponseEntity<MessageResponse> addBank(SwiftCodeRequestDTO dto) {
        if(bankRepository.findBySwiftCode(dto.getSwiftCode()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new MessageResponse("SWIFT code already exists"));
        }
        else if(dto.fieldsNotEmpty().isEmpty()){
            Bank bank = new Bank();
            bank.setCountryISO2(dto.getCountryISO2());
            bank.setSwiftCode(dto.getSwiftCode());
            bank.setCodeType("BIC11");
            bank.setBankName(dto.getBankName());
            bank.setAddress(dto.getAddress());
            if(!bankRepository.findAllByCountryISO2(dto.getCountryISO2()).isEmpty()){
                bank.setTimeZone(bankRepository.findAllByCountryISO2(dto.getCountryISO2()).get(0).getTimeZone());
            }else{
                bank.setTimeZone("N/A");
            }
            bank.setTownName("Empty");
            bank.setCountryName(dto.getCountryName());
            bank.setHeadquarter(dto.getHeadquarter());
            bankRepository.save(bank);
            return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponse("SWIFT code saved successfully"));
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Missing field in request body: " + dto.fieldsNotEmpty()));
        }
    }
    public ResponseEntity<MessageResponse> deleteBank(DeleteRequestDTO dto) {
        Bank bank = bankRepository.findBySwiftCode(dto.getSwiftCode());
        if (bank == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("SWIFT code not found"));
        }
        else if(bank.getSwiftCode().equals(dto.getSwiftCode().toUpperCase()) &&
                bank.getBankName().equals(dto.getBankName()) &&
                bank.getCountryISO2().equals(dto.getCountryISO2().toUpperCase())) {
            bankRepository.delete(bank);
            return ResponseEntity.ok(new MessageResponse("SWIFT code deleted successfully"));
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("One or more parameters do not match"));
        }
    }
}
