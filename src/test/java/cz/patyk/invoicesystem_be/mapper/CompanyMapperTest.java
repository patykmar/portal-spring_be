package cz.patyk.invoicesystem_be.mapper;

import cz.patyk.invoicesystem_be.dto.in.CompanyDtoIn;
import cz.patyk.invoicesystem_be.dto.out.CompanyDtoOut;
import cz.patyk.invoicesystem_be.entities.Company;
import cz.patyk.invoicesystem_be.repositories.AddressRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.Instant;
import java.util.List;
import java.util.stream.Stream;

import static cz.patyk.invoicesystem_be.mapper.AddressMapperTest.ADDRESS;
import static org.assertj.core.api.Assertions.assertThat;

class CompanyMapperTest {
    @InjectMocks
    private static final CompanyMapper companyMapper = Mappers.getMapper(CompanyMapper.class);

    public static final String NAME = "Testing company name";
    public static final String DESCRIPTION = "Testing company description";
    public static final String COMPANY_ID = "123456789";
    public static final String VAT_NUMBER = "CZ123456789";
    public static final Long CREATED = 915148800L;
    public static final Long MODIFY = Instant.now().getEpochSecond();
    public static final String ACCOUNT_NUMBER = "12345-123456789/0123";
    public static final String IBAN = "CZ1234567890123456789012";

    @BeforeAll
    static void init() {
        AddressRepository addressRepository = Mockito.mock(AddressRepository.class);
        AddressMapper addressMapper = Mappers.getMapper(AddressMapper.class);
        CountryMapper countryMapper = Mappers.getMapper(CountryMapper.class);
        ReflectionTestUtils.setField(companyMapper, "addressRepository", addressRepository);
        ReflectionTestUtils.setField(companyMapper, "addressMapper", addressMapper);
        ReflectionTestUtils.setField(companyMapper, "countryMapper", countryMapper);
    }

    @ParameterizedTest
    @MethodSource("provideDto")
    void toEntity(CompanyDtoIn companyDtoIn) {
        Mockito
                .when(companyMapper.addressRepository.getById(companyDtoIn.getAddress()))
                .thenReturn(AddressMapperTest.ADDRESS);

        assertThat(companyMapper.toEntity(companyDtoIn))
                .returns(companyDtoIn.getId(), Company::getId)
                .returns(companyDtoIn.getName(), Company::getName)
                .returns(companyDtoIn.getDescription(), Company::getDescription)
                .returns(companyDtoIn.getCompanyId(), Company::getCompanyId)
                .returns(companyDtoIn.getVatNumber(), Company::getVatNumber)
                .returns(companyDtoIn.getAccountNumber(), Company::getAccountNumber)
                .returns(companyDtoIn.getIban(), Company::getIban);
    }

    @ParameterizedTest
    @MethodSource("provideEntity")
    void toDto(Company company) {
        //TODO fix-it
        // Warning:(66, 34) java.lang.NullPointerException: Cannot invoke "cz.patyk.invoicesystem_be.mapper.CountryMapper.toDto(cz.patyk.invoicesystem_be.entities.Country)" because "this.countryMapper" is null
        assertThat(companyMapper.toDto(company))
                .returns(company.getId(), CompanyDtoOut::getId)
                .returns(company.getName(), CompanyDtoOut::getName)
                .returns(company.getDescription(), CompanyDtoOut::getDescription)
                .returns(company.getCompanyId(), CompanyDtoOut::getCompanyId)
                .returns(company.getVatNumber(), CompanyDtoOut::getVatNumber)
                .returns(company.getAccountNumber(), CompanyDtoOut::getAccountNumber)
                .returns(company.getIban(), CompanyDtoOut::getIban);
    }

    private static Stream<Arguments> provideEntity() {
        return Stream.of(
                Arguments.of(new Company(Long.MIN_VALUE, NAME, DESCRIPTION, COMPANY_ID, VAT_NUMBER, CREATED, MODIFY, ACCOUNT_NUMBER, IBAN, List.of(), ADDRESS)),
                Arguments.of(new Company(-1L, NAME, DESCRIPTION, COMPANY_ID, VAT_NUMBER, CREATED, MODIFY, ACCOUNT_NUMBER, IBAN, List.of(), ADDRESS)),
                Arguments.of(new Company(0L, NAME, DESCRIPTION, COMPANY_ID, VAT_NUMBER, CREATED, MODIFY, ACCOUNT_NUMBER, IBAN, List.of(), ADDRESS)),
                Arguments.of(new Company(1L, NAME, DESCRIPTION, COMPANY_ID, VAT_NUMBER, CREATED, MODIFY, ACCOUNT_NUMBER, IBAN, List.of(), ADDRESS)),
                Arguments.of(new Company(Long.MAX_VALUE, NAME, DESCRIPTION, COMPANY_ID, VAT_NUMBER, CREATED, MODIFY, ACCOUNT_NUMBER, IBAN, List.of(), ADDRESS))
        );
    }

    private static Stream<Arguments> provideDto() {
        return Stream.of(
                Arguments.of(new CompanyDtoIn(Long.MIN_VALUE, NAME, DESCRIPTION, COMPANY_ID, VAT_NUMBER, ACCOUNT_NUMBER, IBAN, 1L)),
                Arguments.of(new CompanyDtoIn(-1L, NAME, DESCRIPTION, COMPANY_ID, VAT_NUMBER, ACCOUNT_NUMBER, IBAN, 1L)),
                Arguments.of(new CompanyDtoIn(0L, NAME, DESCRIPTION, COMPANY_ID, VAT_NUMBER, ACCOUNT_NUMBER, IBAN, 1L)),
                Arguments.of(new CompanyDtoIn(1L, NAME, DESCRIPTION, COMPANY_ID, VAT_NUMBER, ACCOUNT_NUMBER, IBAN, 1L)),
                Arguments.of(new CompanyDtoIn(Long.MAX_VALUE, NAME, DESCRIPTION, COMPANY_ID, VAT_NUMBER, ACCOUNT_NUMBER, IBAN, 1L))
        );
    }
}