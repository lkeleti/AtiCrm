package dev.lkeleti.aticrm.service;

import dev.lkeleti.aticrm.dtos.CreatePartnerCommand;
import dev.lkeleti.aticrm.dtos.ModifyPartnerCommand;
import dev.lkeleti.aticrm.dtos.PartnerDto;
import dev.lkeleti.aticrm.model.Partner;
import dev.lkeleti.aticrm.repository.PartnerRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service
@AllArgsConstructor
public class PartnerService {

    private PartnerRepository partnerRepository;
    private ModelMapper modelMapper;

    public List<PartnerDto> listAllPartner() {
        Type targetListType = new TypeToken<List<PartnerDto>>(){}.getType();
        return modelMapper.map(partnerRepository.findAll(), targetListType);
    }

    public PartnerDto listPartnerById(long id) {
        return modelMapper.map(partnerRepository.findById(id), PartnerDto.class);
    }

    public void deleteById(long id) {
        partnerRepository.deleteById(id);
    }

    public PartnerDto createPartner(CreatePartnerCommand createPartnerCommand) {
        Partner partner = new Partner(
                createPartnerCommand.getName(),
                createPartnerCommand.getPhone(),
                createPartnerCommand.getCity()
        );
        return modelMapper.map(partnerRepository.save(partner),PartnerDto.class);
    }

    @Transactional
    public PartnerDto modifyPartner(long id, ModifyPartnerCommand modifyPartnerCommand) {
        Partner partner = partnerRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Can't find partner")
        );

        partner.setName(modifyPartnerCommand.getName());
        partner.setPhone(modifyPartnerCommand.getPhone());
        partner.setCity(modifyPartnerCommand.getCity());

        return modelMapper.map(partner, PartnerDto.class);
    }
}
