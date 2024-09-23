package in.dhirajrajput.service;

import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import in.dhirajrajput.response_request.UserDto;

public class UserArgumentProvidor implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext arg0) throws Exception {
        return Stream.of(
                Arguments.of(UserDto.builder().userName("Sachin").password("12345").build()),
                Arguments.of(UserDto.builder().userName("Akash").password("12345").build()));
    }

}
