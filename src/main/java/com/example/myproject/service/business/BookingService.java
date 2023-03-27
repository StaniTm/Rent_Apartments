package com.example.myproject.service.business;


import com.example.myproject.domain.entity.BookingEntity;
import com.example.myproject.domain.model.banding.business.BookingAvailabilityForm;
import com.example.myproject.repository.BookingRepository;
import com.example.myproject.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;


@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    private final OfferService offerService;
    private final UserService userService;
    private final ApartmentService apartmentService;

    @Autowired
    public BookingService(BookingRepository bookingRepository, OfferService offerService, UserService userService, ApartmentService apartmentService) {
        this.bookingRepository = bookingRepository;
        this.offerService = offerService;
        this.userService = userService;
        this.apartmentService = apartmentService;
    }

    public boolean createUserBooking(BookingAvailabilityForm form, Long offerId, String username) {
        List<BookingEntity> offerBookings = this.bookingRepository.findAllByOffer_Id(offerId).orElse(null);
        //check if date is available
        boolean isAvailable =
                offerBookings == null || offerBookings.stream().anyMatch(booking -> booking.getBookedOn().compareTo(form.getViewDate()) == 0);

        if (isAvailable) {
            return false;
        }
        this.apartmentService.setIsBooked(this.offerService.getModelById(offerId).getApartment());

        BookingEntity booking = new BookingEntity().
                setClient(this.userService.getEntityByUsername(username)).
                setBookedOn(new Date(form.getViewDate().getTime())).
                setOffer(this.offerService.getEntityById(offerId));

        this.bookingRepository.saveAndFlush(booking);

        return true;
    }

}
