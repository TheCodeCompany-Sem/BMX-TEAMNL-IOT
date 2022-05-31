# Weather proofing

As we need to make the device work outside, we need to make sure it is weatherproof. To make the weather station weather proof, it needs to be able to resist the following:

- (heavy) rain
- Strong winds and gusts
- Possibly lightningbolts?

## Rain

Making the product waterproof is practically impossible, as you need to be able to connect the wires from the wemos to a device, however making it water-resistant is definitely possible. This makes it practically impossible for rain to get into the device. To do this we can use potting compound, like [this one](https://www.f4p-products.com/products/4319079-2-x-150ml-bottles-of-magic-gel). It will make it IP68 rated water resistant, which is the same as all high-end smartphones have nowadays, making it perfectly capable to resist the rain.

## Wind

Making the device resistant to wind is simply making a good enclosure and finding a way for the wind sensors to stand upright without the risk of it falling over. The average windspeed at our location is during the winter (the wind is stronger during the winter) around 13 to 16 km/h, which is not all that much. but gusts can get to 75 and even 100 km/h. The base of the windsensors is made of metal, which I'm sure can hold against such windspeeds. The bigger problem is actually attaching this base to the ground/track in a strong enough way that it stays attached and upright. This would also have to keep in mind that the other parts of the device would also need to be attached to it.

## Lightning

Making it resistant to lightning would be very difficult, as a lightning bolt would completely destroy the device. However, the device is already sort of protected against lightning, as the top part of the wind sensor is made of plastic, which does not conduct electricity. In addition to that, we should make sure the device is not the highest point in the nearby area, which would make it highly unlikely to be struck by lightning.  
There are ways to also protect the device if it still gets struck by lightning, however it is definitely not easy. To do this we'd need to lower the current gotten from the lightning and also move the extra voltage down to earth. Due to the low voltage of the arduino and wemos, the latter can only be done using a gas tube surge arrester. This moves the extra voltage and current to earth. However, this can only work with a full heavy duty grounding system, which would need a lot to be set up. Then, we also need to move the extra voltage down to earth. The most common way to do this is by running a ground wire from the top of the antenna, but this wire would need to have a braided metal strap and be at least 5cm (2 inches) wide to make sure it at least has a chance of not evaporating from the impact.
