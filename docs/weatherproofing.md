# Weather proofing

As we need to make the device work outside, we need to make sure it is weatherproof. To make the weather station weather proof, it needs to be able to resist the following:

- (heavy) rain
- Strong winds and gusts
- lightningbolts

## Rain

Making the product waterproof is practically impossible, as you need to be able to connect the wires from the wemos to a device, however making it water-resistant is definitely possible. This makes it practically impossible for rain to get into the device. To do this, we could make a casing for the device, with **rubber rings** around the openings, this way the rubber can catch the water comming through the holes. However, this would give us a problem regarding heat, as if it gets warm weather the heat would then make the casing expand, which would allow water to find its way to the board anyway.
Apart from that, we could also use some other methods, one of which is using **nailpolish**, which would could also make the board resistant to water, but it would not be able to protect the usb port, nor would it be able to protect the pins.  
Another option would be to use **silicone** or **potting compound**. We can dip the board in there and make a brick of silicone with the board in there, which would actually make the device as waterresistant as possible and would even make it function completely underwater. The problem with this however, is that we need to be able to access the usb port of the board, which would be inaccessible. Modifying the board afterwards would also be impossible.

## Wind

Making the device resistant to wind is simply making a good enclosure and finding a way for the wind sensors to stand upright without the risk of it falling over. The average windspeed at our location is during the winter (the wind is stronger during the winter) around 13 to 16 km/h, which is not all that much. but gusts can get to 75 and even 100 km/h. The base of the windsensors is made of metal, which I'm sure can hold against such windspeeds. The bigger problem is actually attaching this base to the ground/track in a strong enough way that it stays attached and upright. This would also have to keep in mind that the other parts of the device would also need to be attached to it.

## Lightning

Making it resistant to lightning would be very difficult, as a lightning bolt would completely destroy the device. However, the device is already sort of protected against lightning, as the top part of the wind sensor is made of plastic, which does not conduct electricity. In addition to that, we should make sure the device is not the highest point in the nearby area, which would make it highly unlikely to be struck by lightning.  
There are ways to also protect the device if it still gets struck by lightning, however it is definitely not easy. To do this we'd need to lower the current gotten from the lightning and also move the extra voltage down to earth. Due to the low voltage of the arduino and wemos, the latter can only be done using a gas tube surge arrester. This moves the extra voltage and current to earth. However, this can only work with a full heavy duty grounding system, which would need a lot to be set up. Then, we also need to move the extra voltage down to earth. The most common way to do this is by running a ground wire from the top of the antenna, but this wire would need to have a braided metal strap and be at least 5cm (2 inches) wide to make sure it at least has a chance of not evaporating from the impact.
