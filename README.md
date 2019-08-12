# Blom's scheme implementation
https://en.wikipedia.org/wiki/Blom%27s_scheme

Laboratory work for ETU university

```
Alice : Sending message : Unknown receiver : Bob : Creating encryption key ...
Alice : Sending message : Text : Hi bob, it's my first message
Alice : Sending message : Encrypted message : [78, -72, 110, -48, -109, 35, -31, 67, -116, 49, 109, -108, -23, -76, -29, 85, -55, -112, 106, 94, -40, -124, -11, 84, -107, -68, 14, 87, 112]
Bob : Receiving message : Unknown author : Alice : Creating encryption key ...
Bob : Receiving message : Data : [78, -72, 110, -48, -109, 35, -31, 67, -116, 49, 109, -108, -23, -76, -29, 85, -55, -112, 106, 94, -40, -124, -11, 84, -107, -68, 14, 87, 112]
Bob : Receiving message : Decrypted text : Hi bob, it's my first message
Bob : Sending message : Text : Hi Alice, nice to meet you
Bob : Sending message : Encrypted message : [18, 16, 21, 47, -104, 65, -49, 27, -22, 29, 18, 50, 31, -106, -54, -73, 43, -37, -48, -51, 1, 86, -34, 52, 87, -53]
Alice : Receiving message : Data : [18, 16, 21, 47, -104, 65, -49, 27, -22, 29, 18, 50, 31, -106, -54, -73, 43, -37, -48, -51, 1, 86, -34, 52, 87, -53]
Alice : Receiving message : Decrypted text : Hi Alice, nice to meet you
Bob : Sending message : Unknown receiver : Charlie : Creating encryption key ...
Bob : Sending message : Text : Hi, Charlie, where are you?
Bob : Sending message : Encrypted message : [64, 91, -128, -15, -97, -107, 100, 32, 9, -43, 13, -33, -31, -99, -127, -22, 46, -104, 32, -116, -50, -15, 1, 36, -91, -77, -4]
Charlie : Receiving message : Unknown author : Bob : Creating encryption key ...
Charlie : Receiving message : Data : [64, 91, -128, -15, -97, -107, 100, 32, 9, -43, 13, -33, -31, -99, -127, -22, 46, -104, 32, -116, -50, -15, 1, 36, -91, -77, -4]
Charlie : Receiving message : Decrypted text : Hi, Charlie, where are you?
```
