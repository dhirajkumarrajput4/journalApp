package in.dhirajrajput.service;

import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import in.dhirajrajput.entity.User;

public class UserArgumentProvidor implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext arg0) throws Exception {
        return Stream.of(
                Arguments.of(User.builder().userName("Sachin").password("12345").build()),
                Arguments.of(User.builder().userName("Akash").password("12345").build()));
    }

}
