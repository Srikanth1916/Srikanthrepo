package io.skyhive.veneer.common.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.skyhive.veneer.common.entities.location.LocationDto;
import io.skyhive.veneer.common.exception.LocationUnidentifiedException;
import io.skyhive.veneer.common.rest.LocationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author krishna
 * @created 20/12/21
 * @project skyhive-veeneer
 */
@Service
@Slf4j
public class LocationService {
    private final LocationRepository locationRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public LocationService(LocationRepository locationRepository,
    ObjectMapper objectMapper){
        this.locationRepository = locationRepository;
        this.objectMapper = objectMapper;
    }

    public DecryptedLocation getLocation(LocationDto location)
            throws  LocationUnidentifiedException {
        try {
            if (location.getLocationHandle() == null) {
                location =
                        locationRepository.getLocation(location.getCountryInfo(),
                                location.getFormattedAddress());
            }
            String decryptedString =
                    AESCoder.decryptString(location.getLocationHandle());
            log.info("decrypted String " + decryptedString);
            return objectMapper.readValue(decryptedString,
                    DecryptedLocation.class);
        } catch (InvalidAlgorithmParameterException | NoSuchPaddingException | IllegalBlockSizeException | NoSuchAlgorithmException
        | BadPaddingException | InvalidKeyException | JsonProcessingException ex){
            throw new LocationUnidentifiedException("Location unindentified");
        }
    }
}
