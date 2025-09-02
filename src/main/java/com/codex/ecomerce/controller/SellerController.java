package com.codex.ecomerce.controller;

import com.codex.ecomerce.config.JwtProvider;
import com.codex.ecomerce.domain.AccountStatus;
import com.codex.ecomerce.exceptions.SellerException;
import com.codex.ecomerce.model.Seller;
import com.codex.ecomerce.model.SellerReport;
import com.codex.ecomerce.model.VerificationCode;
import com.codex.ecomerce.repository.VerificationCodeRepository;
import com.codex.ecomerce.request.LoginRequest;
import com.codex.ecomerce.response.AuthResponse;
import com.codex.ecomerce.services.AuthService;
import com.codex.ecomerce.services.EmailService;
import com.codex.ecomerce.services.SellerService;
import com.codex.ecomerce.util.OtpUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sellers")
public class SellerController {
    private final SellerService sellerService;
    private final VerificationCodeRepository verificationCodeRepository;
    private final AuthService authService;
    private final EmailService emailService;
    private final JwtProvider jwtProvider;


    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginSeller(
            @RequestBody LoginRequest  req
            ) throws Exception {
        String email = req.getEmail();
        String otp = req.getOtp();

        req.setEmail("seller_"+email);
        req.setOtp(otp);
        AuthResponse authResponse = authService.siging(req);
        return ResponseEntity.ok(authResponse);
    }

    @PatchMapping("/verify/{otp}")
    public ResponseEntity<Seller> verifySellerEmail(@PathVariable String otp) throws Exception {
        VerificationCode  verificationCode = verificationCodeRepository.findByOtp(otp);

        if((verificationCode == null) || !verificationCode.getOtp().equals(otp)){
            throw new Exception("wrong otp..");
        }

        Seller seller = sellerService.verifyEmail(verificationCode.getEmail(), otp);
        return new ResponseEntity<>(seller, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Seller> createSeller(@RequestBody Seller seller) throws Exception {
        Seller savedSeller = sellerService.createSeller(seller);

        String otp = OtpUtil.generateOtp();
        VerificationCode verificationCode=new VerificationCode();
        verificationCode.setOtp(otp);
        verificationCode.setEmail(seller.getEmail());
        verificationCodeRepository.save(verificationCode);

        String subject = "Kp Mart email verification code";
        String text = "Welcome to Kp Mart, verify your account using this link";
        String frontend_url = "http://localhost:3000/verify-seller/";
        emailService.sendVerificationOtpEmail(seller.getEmail(), verificationCode.getOtp(),subject, text+frontend_url);

        return new ResponseEntity<>(savedSeller, HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Seller> getSellerById(@PathVariable Long id) throws SellerException {
        Seller seller = sellerService.getSellerById(id);
        return new ResponseEntity<>(seller, HttpStatus.OK);
    }

    @GetMapping("/profile")
    public ResponseEntity<Seller> getSellerByJwt(@RequestHeader("authorization") String jwt) throws Exception {
        Seller seller = sellerService.getSellerProfile(jwt);

        return new ResponseEntity<>(seller, HttpStatus.OK);
    }

//    @GetMapping("/report")
//    public ResponseEntity<SellerReport> getSellerReport(@RequestHeader("authorization") String jwt){
//        String email = jwtProvider.getEmailFromJwtToken(jwt);
//        Seller seller = sellerService.getSellerByEmail(email);
//        SellerReport sellerReport = seller
//    }

    @GetMapping
    public ResponseEntity<List<Seller>> getAllSellers(@RequestParam(required = false)AccountStatus status){
        List<Seller> sellers = sellerService.getAllSeller(status);

        return ResponseEntity.ok(sellers);
    }

    @PatchMapping()
    public ResponseEntity<Seller> updateSeller(@RequestHeader("Authorization") String jwt, @RequestBody Seller seller) throws Exception {
        Seller profile = sellerService.getSellerProfile(jwt);
        Seller updatedSeller = sellerService.updateSeller(profile.getId(),seller);
        return ResponseEntity.ok(updatedSeller);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteSeller(@PathVariable Long id) throws Exception {

        sellerService.deleteSeller(id);
        return ResponseEntity.noContent().build();
    }
}
