# Encoder / Decoder Ring (Vigenère Cipher)

## Overview
This project implements both encoding and decoding for a Vigenère cipher in Java. Given a text message and a key, the program can either encrypt or decrypt the message using character-based substitution.

The goal of this project was to practice low-level string and character manipulation while reinforcing how classical encryption techniques work under the hood.

## Concepts Demonstrated
- String and character processing
- Arrays and index alignment
- ASCII arithmetic and modular math
- Input validation
- Separation of encoding and decoding logic

## Implementation Notes
- Encoding and decoding are handled by separate classes for clarity and maintainability.
- Input is normalized so that uppercase and lowercase letters are treated consistently.
- Non-alphabetic characters (such as spaces and punctuation) are preserved and do not consume key characters.
- The cipher key is cleaned to include only alphabetic characters before processing.

## Reflection
Working on both the encoder and decoder highlighted how small changes in character arithmetic can completely alter program behavior. This project reinforced the importance of careful index management, clear logic flow, and defensive input handling when working with text-based algorithms.