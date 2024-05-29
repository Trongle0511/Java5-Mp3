package com.example.music_mp3.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.Objects;

/**
 * Utility class for encoding and verifying passwords using BCrypt.
 */
public class PasswordEncoderUtil {

    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * Encodes the raw password using BCrypt.
     *
     * @param rawPassword the plain text password to encode
     * @return the encoded password
     * @throws IllegalArgumentException if the rawPassword is null
     */
    public static String encodePassword(final String rawPassword) {
        Objects.requireNonNull(rawPassword, "rawPassword cannot be null");
        return passwordEncoder.encode(rawPassword);
    }

    /**
     * Verifies that the raw password matches the encoded password.
     *
     * @param rawPassword    the plain text password
     * @param encodedPassword the encoded password to match against
     * @return true if the passwords match, false otherwise
     */
    public static boolean verifyPassword(final String rawPassword, final String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
