package com.fisa.land.fisaland.market.service;

<<<<<<< Updated upstream
=======

>>>>>>> Stashed changes
import java.time.LocalDate;
import java.util.List;
import com.fisa.land.fisaland.market.dto.MarketReviewDTO;

<<<<<<< Updated upstream
public interface MarketReviewService {
	
    List<MarketReviewDTO> getAllReviews();
    
    MarketReviewDTO getReviewById(Long id);
    
    MarketReviewDTO createReview(MarketReviewDTO reviewDTO);
    
    MarketReviewDTO updateReview(Long id, MarketReviewDTO reviewDTO);
    
    void deleteReview(Long id);
	
    List<MarketReviewDTO> getReviewsByUserId(Long userId);
	
=======
@Service
public class MarketReviewService {

    @Autowired
    private MarketReviewRepository marketReviewRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<MarketReviewDTO> getAllReviews() {
        return marketReviewRepository.findAll().stream()
                .map(review -> modelMapper.map(review, MarketReviewDTO.class))
                .collect(Collectors.toList());
    }

    public MarketReviewDTO getReviewById(Long id) {
        MarketReview review = marketReviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));
        return modelMapper.map(review, MarketReviewDTO.class);
    }

    public MarketReviewDTO createReview(MarketReviewDTO reviewDTO) {
        MarketReview review = modelMapper.map(reviewDTO, MarketReview.class);
        MarketReview savedReview = marketReviewRepository.save(review);
        return modelMapper.map(savedReview, MarketReviewDTO.class);
    }

    public MarketReviewDTO updateReview(Long id, MarketReviewDTO reviewDTO) {
        MarketReview existingReview = marketReviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        existingReview.setContent(reviewDTO.getContent());
        existingReview.setRate(reviewDTO.getRate());
        existingReview.setUpdatedAt(LocalDate.now());

        MarketReview updatedReview = marketReviewRepository.save(existingReview);
        return modelMapper.map(updatedReview, MarketReviewDTO.class);
    }

    public void deleteReview(Long id) {
        marketReviewRepository.deleteById(id);
    }
>>>>>>> Stashed changes
}
