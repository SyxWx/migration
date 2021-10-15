package com.bme.syx.threadteset;

import sun.misc.BASE64Decoder;

import java.io.FileOutputStream;
import java.io.OutputStream;

public class image {


    public static void main(String[] args) {


        String  base54str = "/2qyEkM8wyZVDAIe9c14hhsV1uXTrJEaK\n" +
                "WdUXbwMd/wDCmRuO0a0TTNMsdqlXkjLP9Sa17RPtfnIOS3J9/aqEN39ouVgT/lmwU8Vs2Fisd+bt\n" +
                "Gddp4wOOPWmST6baaXNatMhIuFbGxeAK534seGpfFXw9ubL7H5uoaKVvLJw3zH/nsv8A3zzj1UV0\n" +
                "thIFCxSsi/aHaQnoc1ciuLeO3uZ7qQERvGNo7jNZ1ou10Lc+SNTvom0638PaKuVwr3LjrLLWRb28\n" +
                "glMM0RBA9K67xP4ZtfCfjnUdIM222jk861cA8xNyo/Dp+FUfItIGk+03T+a3zruTj865U+RWRkZt\n" +
                "vaSNEFeN+MgNjita0hzCLfzIovOYfNIudpHepNNnLweXE5EJbJ9zSzQvvUnG2NskVi562JL2m3Gn\n" +
                "aQJXuIF1B/8Anqp28+1XIZJ9StWnuoGeE/cRj2rM8HW0t9q1nZTqgidnkff+n9K9OsNEKx7jJCUH\n" +
                "Jy2Kp8sTSMDi9IijttRimEEsIU/dcHFej2Vo92izrIcb9tMg0hL22ScIJBW7pVjGkPy/Lile6Omn\n" +
                "HlJ7O2ERXNwgH+01MuAs0ywxyBzn+Fs1faCBoHRggwuelQaLZokj3Cj2ryM2xiwuHlI9TL8O8RVS\n" +
                "NmFPKhjQfwjFTjjmmD7nWlDZr8klJ1Jcx95FKEdB3Sng/KMUzOKVWyKkroSDvilBpgbApQR1NSND\n" +
                "yeOTVG8m3jygc1ZmkEYJ9qyN5ZsmnFXGKo2ipGbC1Eoy3WiWTC1rYZXuJCF8sdhgVJEqqODUMY8y\n" +
                "fP41a2qAuPxrXYSAmowATlUApTIAwGCc0qqVUHNMof8A8s6M8AUdqbkDtSGOHNPXimA7R0pdxoAX\n" +
                "tikAwKB0GaXnbQA4j/azxTO+KCePegDvmgBR93Hek5pabgk4DUAOB9aUc8GkAIoGQaQDu/SlXHem\n" +
                "5HSlBGOlACgnnmlHQCm/jT++KQCjgU4cZpimnDpSAUdKhuLgL8q9afNJ5aE5rPkbcdwNVGNyZuwo\n" +
                "P7x3YZwhxSL0564ppbHPtT+5ArqhA5mw4NOUUznitDSLSW6ukijiLljgADqa9LDYX2srIxm+XU3/\n" +
                "AAD4am13ULdGUvCg3XBH8PpXvFhptpbWvkwxTKyjCqo+XFY3g7SIfD2nqkvkRNn5i5AycCumlvII\n" +
                "h+41qO3f1WEyV9/l2CVOGx8jmGJ9vOyKj2107Y+xSsG4HIq2U8mMR/YY4fXkZasu9u4vOEqTX8wb\n" +
                "r5UTKKS1ukiMUWneHpzG53SGXK4/HtXrS+GxxKGhppFBuVVkjUg5K1YEhdjtbI4xiqn72PM0lgsK\n" +
                "4xnfTrGWPzxG8yoF569alRsKw2bk4awaRTwW34qoLMQhZLSzsXVu8wzRcSR7piHZ9xOQRils3soo\n" +
                "4zJp2QvGVP8ASmhFny/LiMsj2MDdhHD1pklxPNAAHd4h/s8U+JjBcf6FsTqQWqzcG8ldVnuw2Tn9\n" +
                "1EFpgUbfbHOGDuQQQcjFTywb4zLuypHSmTREEiVHeo/LZcLsZfQUASLAgZI/MXn1qveQIiu4Q7l7\n" +
                "56UyXR4p5fNKuOO3SiMQpHsDlkOARTAcv3FbB5FAm2tz92leeLcpU7kQVRiKIr5OAPagLFsyRNKT\n" +
                "AxKj17VA0ayb96naw5qL7Uic7Tz7U7z1CozNjdwKAJoY0QKiYVB60+faHIGMccrUW5WOxWHIqSGM\n" +
                "tGFLffpCBIbbzcrctvP8G2rFvaebJ/rVUY71BJC6Orl/alg35KqCw9zQAfYVt9+y7IyScrTYRtVp\n" +
                "ZLgvuXaRirMqnaflNUTaSKdwkOw9RQBIz7soj7aal3znGR0zUMlqxyPaqp87HllH2g9hTQzXkg3w\n" +
                "tIBx3qogy2QKsWl40MbRyL5kbL3quWwQRx3oAewcPuRsGrUcxEec9RUC3CbuUzu5pYlZjtCmkBNL\n" +
                "NIsKncvIzUavN5e4n5cVFLuZ1BQjAwKevyLimAHY8fzjIqRFREaVTgDrTNr/AHlfA9PWmP5mzaG+\n" +
                "tMCMyRpIWyST3qCV++5fxNTm1ZuA1VbiFEXM+Dg8UxWF3YG8Y4qk5Ltlj1q2AM4H3abtHKlce9MR\n" +
                "AilMRuD83Q1MmYhubmmCBZVXyp9x6YNQFDC/3dp71QG5a3cUsW53FRzzNFF5uzenrWVFNsxhsj+7\n" +
                "ViO/aGEwxk7JOWUng1IE4ZbtmkPBKdcVHGRauJJJQAp44pDeK6j5AnsKRyrL8/Ap2EXI9QSTdIEH\n" +
                "1Peov3bNvd9i9qqxfZclVibeP4jVmCeWzG8HcD2NHLYYOpQDYCwqdZnVd8qAcelR+fHcnam6mTK3\n" +
                "yjzXG0YosBP5jyjco+U1F5UpGNwqRA6DbKyL9GzURnTO1I2b68Ciwh4bEgGeMU7BHfNRsHYYEYxi\n" +
                "mBQNz9cUD3LIuYU3B9xbb0AqLzp5APJjRPduSKWKb7UCEjWP5cZphVv7+aLAkAubf5fteqvwceWF\n" +
                "6/8AfNWFZA4ubW2HHyqzrUMOxHICIPTims07T75pWYdh6VQWLr+bcp++v0Vj/Cg21ViVQdhds+5z\n" +
                "RxjfgZNIksL/ACsDu9dtBI+WMtyzvx6VHHtV/m5+tPZW27IulLiIcsKBWJ1jjIyvWhmhjH7xc+1V\n" +
                "XKbgqmm5QgAgMR60x2JJZoXcBVpqSNJOsQYYIpI4HkIfKAjpk0gWOF902NwGMimhE3lfeTrVaWwD\n" +
                "IvnR5C+tTvdMMNHGGBHWnyuWXcT0oEVFj2gRo4C9afIq70mEhb+9npUykAMSi80PHvj/AHKoD71Q\n" +
                "EEmxWZTihcOAD1qKZIgSbsE454qyvlSRB4FbZ70CGlgkKxnoPQUQfZl3uLddwPU1L5QEmSuRimsp\n" +
                "bjZjJqgB2juBiVB5fsaVZbRIfIgiZ9vA3GohLlhE1tx60RTOrktjYp5FBNiYWaMuZZDH9KoyvBHJ\n" +
                "5NnKWkbuy8VpmS2mtWkEqxbe7nArL1DxFpWnOuJTdJ90tDyKaCxLIkhiRHbnOTipBJbzSlowQAdv\n" +
                "NIs0VzGl1bIyB16N1FNhWG2MZYZzuLH1NUhFqaVIfLCMj/w4HNPxHJvCosW3pxTI7KJE861BzQhk\n" +
                "i/1xCv8AdG+mhEZ/djb5hkx3NRF12ZDrx71YkZS4Xaq/7tU76wkkh/0O6ihmDbvnXIcelUIvPeWC\n" +
                "W6xTW6Sll4bHSqLxJOYnhcrgdFpbaxM9urlkTH3w39KdGzYD25XKdOKYxYP9HmbbITvGDuFQNlJ1\n" +
                "5JLdhUlxxJulspXJI+dPWpYxbl0L7t6crQAOg2FwozSebGtuFlH40nkb2/1yjjiqv2KT+z0Hmme4\n" +
                "ilbJPpSQia3P7snYfmqSNyF2k5rOhs1mnM87uksXCnfxVpVm4jiTeNvLE9Kdhkk25x8j9qYnHyE8\n" +
                "YqOJ1aNN+4etKqCTdhun3RSsIjeMlldWOQwYUGTZMV3NukOfrTnEjPGAG49KzLzW9Gtr+KK7vozI\n" +
                "pwYYv3kmfotOwE88d1bJ8spZX7UspjhsheXMwt0Rf3ryNtCiuY1Lx34nLNDofw1uru2zhZ7q5ERH\n" +
                "vsxu/WktdJn1qcXPiu3T7QmHW3WUtHH6AD/GnyaBYfca5rN/dpZ+C9D+3lxtOoXOUto//iv89ar2\n" +
                "fhG8vNZik8cXEl9DbR7hap/qGl9fWupgP72MWyhQn3VNVovEvnXJ02+t1jYSbNy9vQ07dhEE94sM\n" +
                "y2VvYw6dZ42xraxD5fw6Vde30690oaXpmpTfaw4bdcnB+gqWfT92VCCRz93PestTayXJimsDbXCH\n" +
                "50bv+NCSYF2K+mtDHZzRoJAnPPFNl12OeRLW3tvn/izVSZ5pQZni27OF47Ur6fdSXq3bKkY+RjzV\n" +
                "WQDLNdYWaW4vP9XL90dDH7VpWksd4twhHyoobNULnV5o3FtIjOhbbvbmrN1bw3LteQP5cjRYZFPU\n" +
                "+9KwhwSGxh8qDZ+8+b/aqu0CzXUUvmMD1FIr74Xa9EaBU69KoMiX2DI9xHjHl+U/aq2A0Zbu1tox\n" +
                "cPG8nOOKLN5bhFu306GP5yEOMOFqbasHliMfu8dT61QguNTs3liuYllz8yYalYCpqWoR6YXXcZD0\n" +
                "VTyM1Nb75Io77ZiUr3/hNWVeWCNLizjjDScsueTWfC91O7yXEX2dxJxj7rCgZppvMBWV0EvX5Wzx\n" +
                "TN7xwtNnBH+1SSRxT7zax7XwME0XemRXtvFEZ3Ev8aqPvUhEl9dvElteB1DbeY/61FNItzbXEk82\n" +
                "zO0HHpUs62Mkcmnw3trBeeWNqznDdPSqEn2ZmRDNx5Y3lfu5poLCFtPubyOe8vW8qNOYs9adlLy4\n" +
                "S6FsyonKlhUMOm2cEqPBKZZGcMyHGQKszXjQWUlyYf3e7ag3U7AQ3d3Kl5BHahJSn7x/mprZtleX\n" +
                "yzJNKRS2s1k7b/sv73bxj0qH7NLcMHCsgjJbbRYC6ZNkKxo+x0+Z6lAjlTek7cj7tUVhyyymYY/i\n" +
                "Wp7YiJWeABvMOOTSsIzzqMdtcNa3SuZZIyY8DP8A+qtKHMbx/uvnK9M9KpxSRW2ZnB83GPrRb6kj\n" +
                "OZWTfIwwnsaoY2dJ1LrAqO2eDu6VU1C3n3WybvNJbkgVctLe6jlQhVIQFZPm/Wl/0tlkKRZbOPlq\n" +
                "QKlyEEbxtF0HBWqsP2uIHyZ5opXGMRnk1pT6WPs5lmaVX6nFVNzwwJ9ilkd+mSBQAy2urm8t2v7g\n" +
                "fZ/KXYhI5b8KoReKrGDSo7G/TybeLhjWZrOrDw2Jm1XVZLq6m+aK2HavLvEnia5nna4vpcsf9VAh\n" +
                "6VcYcwHV+KvHn2q2nsNPIstKjGCx+/LXmFzqmoalLHa6enlQfTlqQwX2sSb5X+i9hXTaVoKJsgTc\n" +
                "zN3HWlKoqatECt4f8PTfaFxAZZiehFel6H4K2y/bL3Idui+grQ8JeHoNMhWRVIdh/rH5rqbMhZVM\n" +
                "uDg1yNymxpGI/haVuRfGOPOduOa1GglMIQZkx/FnpTrqae4n2hSmOgFRw6ZcfuriWV1boVU4q4xs\n" +
                "UU73R7IqXutUuFVjgAdjTbJ/s8UdpdywvauNqvVhtPE0ciXTkBpM571f0uysbqzaK7sImWFvlb1r\n" +
                "QkhREex2WF/D8mc7jimfbLW1tUS4gRm/2UzVdLaVb+4aK0xaSALnPSrOlyw3FxNYq+ecDIpWERre\n" +
                "+dNuWVdgHAaqGoXUy3SfYbjy4pOoHOKtlYIr8/aE37TgelTNpun21pG1xLsMrHbtFVsBkGSQ3H2u\n" +
                "PzmK9Xxxin3rXusKA1z8v93pirsabYmt43dk7MaljhdMoJ0dRjAAwRU2Ay4jd2KyR+Rkt/GxqvPF\n" +
                "cTwBITHI/q7VZuY7k35t5n5xkVas7CTzELbFGKoCayuZBAsc0Qj42g4qCaCPB8iTZuOfu9amYs8Y\n" +
                "hcKxVsCk8i5kfygIk3cByc4osIyTpUepM8F1BE0HVh0Oax9Y8PT2W+40S9uI5cfJG0u5f1rpnsZI\n" +
                "JiGuRKx7pxTZbL5VdpdxI4Bpp2Eec+V43gnhu9ZlDQI4yd2Qo/Cush1ewluFs7G4EknUkdM1sT2l\n" +
                "xc2f2SUBhJwVxwawbHw7eaPa+TPaRSgZ/eR/6xaq99wNbVLm++xyHzZDLJGec1jWglTTt00pDYyA\n" +
                "e1aOn6pYQoT/AGsly0XLQznDgd6y73xF4a1m8+x6VcSpI33hLHtX3o8gCw0uWE7mYXIn+br0qea3\n" +
                "EJYCFcO31p9rOEP+i4fylxuzWhbwWuTLcTvIx9qBWMyTy4Zd0YHTtUlpp8G7zDF+8J7d6Li2COfL\n" +
                "bJb36CtCxVYNl1Jk+X81AyxqdtNpNlHJOu15BkD0rzPW9TuGGVfAMh4xXXeNPEsl7bo8a4Yt2Pav\n" +
                "NddvxbwH19M1K8yzP1zWrkRrYQMXafAwBkn2rvvB/g0aBpH2u8f/AImNwN7J/cB/hri/BEP2e9i8\n" +
                "RataOixSK0JIyPrXrFvqVtq04urCeOYd6bvsY7mSukzyT+cj+W0pwWPpVw2EaajufdKkWDz0rRdY\n" +
                "vP3syL2HNRyyfvdo2kYplFK+uPMv3gEPlrGobcO9SRMzQ70OFNWYQk7NJKqgL1OajSVC7xIOO1MC\n" +
                "mbuOYeVFuPvUMUTynyWFJcQxrayeVc/P2I65osbl4E+f53YfrT6AKj6fHtZJohjstRalNCtxIisc\n" +
                "rGpDD3qlDbWWnxFjCHmflj6VWguJJIp2uSFLHAJ9KSA//9H48X3py9hUYPanA10lEud3NSBstk1A\n" +
                "DgYqQEbQQaAJgacp+XBqNCaXOKBk4OBigHAFMBp4OKBEi8Y9KfwO1MT7oo7ZNO4x65NKW5GKajEY\n" +
                "HrTm+9UbDGOeOT1rm/El6wT7Eikyy4CY7k1vv0+XtXNCUXOsTX8q5TTIzIPc9qxZRh6xbw/2rFps\n" +
                "XK26hXP+13riPEsD6ProurfgKyuld3pkT3FxJczHc7MWJrK8d6SJbVp15aPDfnXM6lppFW0LqXMU\n" +
                "0drfQP8ALcx7/oa63QLvzrZrU8tGuRXl3g+/MtpNpU3WDMkf+FdvoepCzu4Lgj5eA9XI0gen2bTX\n" +
                "+kD/AEhg1qNpQd/SsmZZ5dUjmaAL9kjwT6se/wDKhJJIPM8uXGa09NhlfSJGb5pbktIXPpRF6A9C\n" +
                "lZRjzMTAnznxla6K3m8vIluGSHP3c8flWDZLhLd512tHJvPPateC4t7KDyQrSNhpNw6nnitDM0DM\n" +
                "WtVESIZd3yn2q0lxb6hmeWwt2UEB2btVGyj+0+UxJVWXP4Gktovs1hJFCXZWn5X27U3awjz/AONW\n" +
                "gxSx6d4xhTHkMtnLjtGeh/PP/fVeVaa12sjblWV+QC43fLX0xq2gWWoQ3/ha/wBz286KuD1OR8u3\n" +
                "05wa+dW0ufTLm+sNTQwvp27cU53AHAP4/wBa82ppoZlVykLeV8q7uwo372mQqAR0Oaf/AGppEcn2\n" +
                "m3055X6RtMc/N9Krk3r3UZlMeZjWHLqTbU6vwpZWcYJms/tEmR8yrlvwrpryOcWavHY3SRAgOzD7\n" +
                "o9/SsjwvLewWYRZjbylmyy10/wBv1yytbm2j1jzkuozE4mTdt+n8qo6IqyM/S9MvUlaEOY9owMTH\n" +
                "pXZ6IZre2NtdPvx9zAxXPeF2uLm9/tK6jSOF4j8oOfaumgmyx2xlu9OT0sb043LFy3QAYLcVesIl\n" +
                "iiwvQ1UiX7TPvbonIq8vyoa/POJMbzy+rxZ9lkeH5aftWWDjbSgiot/yCnxnODXyVj29x3mDI96X\n" +
                "OKaT3FLnP5UiiTdxTs5FR/dIPoaR3OylYogvZAcIDVM9qWdy0hPoaSM7jitIqyCw7O0A1VuJeNnr\n" +
                "VmUhFOe1Uk/fOSe3NXECa2TaAy+lTbsY4psWAFIpHzVbjA+wpy8Limg8DNOBplBuOcUiZOSaaxp1\n" +
                "AIf25oY9Md6TPb0pARSBDweOlG75RTQ3GMUmelADuSKUdBjNJ/CKOooGGaXOO9N7cdqcQQcUALnG\n" +
                "OKVevFNPWlpAL1oyaQdKBQLYVTUmR0pi5CYpVbIBpAOzTgcKaZ2Jx0NRXUjRoNv8VJK4myK7m3t5\n" +
                "eRiqo44pC3elHWuqEeU55SF3ZxkU5c7gQCRTV6U4KePSuiHRIwciSJCzAV6d8N/DUMyNfyq5KfcJ\n" +
                "/vetcl4S8PyaxepGmzb5i/e+or3fTtPtbCBLe1i2heK+zyrB8up4mY4vlXIiVrdZ5Q0kMbrnOHGR\n" +
                "ViQXkDeRptvYiFowCpBH1x+dTLHhKcrBfmI7V9WvcifO+ZBJb6pkMdQhiI7Rw7/51Z+xwFVuJNS1\n" +
                "AseuHCqTUT3DE4WMtTPNdWMeDwM01djbuOa1tBeeY6sVOPvktVvyLK1l3WotwDjdsxWbLdShNqjq\n" +
                "RUsNvCZVaSBetAy3MI7gdVaqu1VUpmpfNCsVVQD1xSK8ksID7PrtpkiWbSwXiSI2KuNeS3H/AB8T\n" +
                "ZA9qpHJO0HHvUYDI5y/P0pBuaJjV/wDU3HmfhVfcxHz9fpUMUsiPlWxVkssicnp1piKU0G9+WPA7\n" +
                "GkMQ8hvl6VPNHLHfAj5o2WleJvJUj+OkBmzROsbN3PSmcoq9eRzUzoInCTTnDdMjpUzWaPH/AK7O\n" +
                "PancCFUhZQofbn1p32IzPH5hBUHNEUEcqLnHFaBt4pv4nT6GmBRCoj7kUEfSk2uZBsO0LzU5gcLt\n" +
                "B8rHQt3qEs6uVJUsO4oWgFqKOydyLy+x6CrnkQRRmaFoWGOnesYSYlVDgsV3VYgnToyE470hE4yY\n" +
                "zD6028tWZw0TBECjrUckqhSQxwKYZRheeopjsRXamaEeWuD7HtVaJSF2Hd+NWsFfnC5qCRSGQpn5\n" +
                "/egBm5mUhs46CpRGlwqrjG0VDK2IwQM0trLJGuwt1oGOhiZgCDjafWrjJ8mUbmq8yrHGsxfapHWm\n" +
                "pKwgyH3huhoDlJcSZ8x15FIXQBjIMjFRRyuitg5D1FJvdThyMEU7CsTpOekQJ471KEjgQJNcDdjO\n" +
                "KzhvCECQg+tS/Z3MZm+1lyB8wagRYDEFwp96i6EblU5HcU1mkGOfrRLjzdyrldtMZG2I+SC30qWK\n" +
                "OPklDyOKbM5U/uYwCB0qGK7a4cbuwz7UBYjFkx5U/Lu5xUc+cke9XVuYIo95i5/2aJLdnzmHhP4g\n" +
                "aYjJCPGwZ42p26PA8wHmrMqbCpkcY96ozzq6/urOXC9GGMUwLHmRbvlHGasW0ko2lmQJ6mqMkNyE\n" +
                "XZs575p32IO+b2/uHX+6hxTEa2LRnzNfRD/dOT+VCS2pIhhkefsCEIqtp1vp9o5lsdLbpje5FX2a\n" +
                "a6+V5kt0xtCqM0AkZ8kGo7hmGOFf727mhnl3FZSWxU8kEFsPvtKD0qBmckxqo2k0AMhk85yPLxt9\n" +
                "+tSxliSrL+tEcctsQH2/hRJ+9yRmgCf7QYyWd0JPpSxksjS54PNU1CMjLzuWkj3hQpbigdiyYThm\n" +
                "DgCmW04SXZywwKSMqSVJxilnmPl7C3TpxSETDBf5EzSSldy725FUHJyX8x/oKmR+mefrVoCV3yoK\n" +
                "mpUubmGI+XJtHemvCZB8vTrio2wUZfTikKxP5kmRNHdZK8lVP86dHeZIDw5DHrVFfm5CrVmMrtwu\n" +
                "OKYWJpDGyMSgzSi2tmUPHFsbHXNReYF+8uaeuTAknIBFAhW39CAMelQlfl5TNSE7fmJNRGQuwHI9\n" +
                "6AsLhtqhUwtSj5lw3f2qLfuXCPnj0pWOIUCn5u9AxzriE9dwPrURYgjbjmn7yV+cZB9KZLEhfzIz\n" +
                "jA6VSEPYeYvzCneQXXc8rCoxIDDnd0pYz5pHJ6UCJR8kYCMxIPf0qSR8NhT2piRIEJ3HOKYcnlTk\n" +
                "Yp3FYmBcgIvGR1FNhtWj5lIbvSKSmx2HalilmLOG7UwKmp6W2qRCEz7VU5GO9YjeEy9w8UYe3GMh\n" +
                "/NLqfwNdMrKVwXxQZPm2oKfM0FitpcctpYJYTkO8ZOHpXfTg2yXVLRZc8xNIFYVZBAbPpWTqHh3T\n" +
                "dajkivbWMsfuyY+ZaSfUQviPQtVulY2GtyWT4GBjcn5VR8PeC9J8LRTXdtJc3Oo3XNxcPI7eYf72\n" +
                "CcCtaC3is4ks47iZwo+XzGzUklxPujjVQAf4q0TYvIZ5dwbdWYh/U46UsP2mJ9sLb93Z+gpJLWLP\n" +
                "necwfPPzHFI8oPyZ6rRcmxYCzBWS4KDd2XtSJCzFPJkGB1xUPnW8PlRq7sx9aWOX5Z9xKiLHPeqQ\n" +
                "rDpTt1DDXG7I3bBTDLdHloRFt6EjOaLgpEvm28PnTBfl3+tRJPeSKJ72MRKo5RTuqgsTgebgyENt\n" +
                "5qBd/msgSWMSEgHFSFkV8RfdlGRimTl8COaZs/eAFCAbFHF5u15xJ65608G4kM2flh4wRTFVVi85\n" +
                "V3HpzUl3cwWFsrXrLHFjLMOmKYEEbDekKxEn2qxeyaPo7ebrWqxWinnk/OfoOtZp13VtVYDQo1so\n" +
                "Mf8AHzPGN/ttWr2n6RBp0rXzXF1e38g+aedgx/CqsI5628Vaxrl29v4YiVbOLP8Apt3EV3eyjvU/\n" +
                "hbwrp+hSXNzE4e8mkLyS7a3rvW7C3x9v8ySR/wCFI8k1F5tvAq3kchEUyh+R2oEOurZfIafLF26Y\n" +
                "rLjsJg8TYTG7589cVctL4E72Jw54z2p13+582O4QS8dM470rWAybyC5knjmtB/qgVbBpI5YVZbi4\n" +
                "sneUDb9zkmmtLHBcxPYW8pa4LdXyPl4qaWORbt5/OZx8j47LmqAsCGWO2+1XQdQnzAbugqPaLnUV\n" +
                "uYzKUZMbTT55ppQY/N3HjIp8K20LLvYqWG0fWkBE1wVfzvKbap2jjNQ3epq0PmTYTa3P0q4jmH5I\n" +
                "5M7RkjFVw0V/dFm+RRxjHFCsBnTXLSzK8CFomCkOF496dMGiie6dX2r3x1q5bqILMlAmFX7tJ9qY\n" +
                "Y87Y8IQbkemBnfbTc6fObi1RQRjrmnadcRLNY6RDEHfZ8zFccY60tz5Xkt5QQCR/4R/DUkE08IL2\n" +
                "4RcrgE/eoCxemvNPtwtnd2EtyJDnjotZkkVx+/vWn/dKQsceO1QD7Q5Lfan9cYFZWr6HrGtTxXE1\n" +
                "7HHYWQ3splI3n14poRfsvJk1QQQwSA7Mhj/DWw9s8sgydwh+9n0qrYrHFBM7OFeMD8qV/Otx9od9\n" +
                "3nR9F7UAVY7lvtUsVvay5i4P9wir0d0AI7gR/wCs5HrRd3VvFGvlxKCMfN3NU7ue2vCpaSVNo+QJ\n" +
                "QBFNZl9Zm1+W0ja4RNiHd/Sr8DQMkE/lqFbrx1qonkum+Wc7idqEjk0xZZbWPy5nURKN3TmgCRYl\n" +
                "ubsXK2/ltbnhvWnXhiXT2tF5KsHxTIdWSRlWGIMG4B6ZqWK4Eu57iDYW+Wgdiuoi3eda8ZGwEVPJ\n" +
                "ZX1mf3N4nk7Ocn56Yh+y3TJtHl53fSoLKSS9u51d/v7ufQUxWGlY5o97sWRhjimOdkeLG2b5BwKm\n" +
                "KJZwmOPMq5CA96ljE0M0VllTv/PpQBnWV9avfytqHyKse0KUzzViK0htYw0A+0L/ALfFNlhEl6wa\n" +
                "BY1zj5TliauRwi2/eTfNHK/3M0AUbfUrt7mSG3sIo4Yv9Y5erdssojacHyxI27ism0nkzPzxPIzb\n" +
                "T2Galvtd0zTLYyahd+REB0CEk/SgC1dalOTIDIzIBn04rzvxF4+t7eJrLw9nz8nfMT8qfSuc8YfE\n" +
                "m61RJLW1c2mnIef7715xe6vPfxmO3XybVfvsOr1olFK7A1dX8UXM924trg3t5If3k7nOPxqHTdIn\n" +
                "uG86VXeRuSTzmq/h/R7nU3/0K1Yoh9R1r1Xw14LuXeKTy87x853cCsKtXsIxfDfhaa7YIkZEh6+g\n" +
                "FesaRoNnpdp5carub7xqzpum2unqsEFuqHoWA5NWJbqOGPcFDVyK8y0h0Ng2wbvljPFTfY7aOSNn\n" +
                "+VIxzVf+0pblPJ8zAI3bcdBWZqd5cEny2JWFQetbRiBr20MMlx5jsVwvFOEkN3FLbRyHzE9q55tT\n" +
                "uwjzxxLlQAmWq3aXN2Jkt3iSGVgOUOc1fKIt3FtcyyRDaCicNg1ZS3KoxRgoNSb/ACYvmBJ3c1F9\n" +
                "riCkgZZexp6hYgljlW2UQy7QXwTjrU0VlbwnzEXcx6nvUNveqbb99HlWPyrUb3ziPfEnXjrQgGb4\n" +
                "zuWaNxlvl461JcqbgRskeFQYGRUbSM9v5ky/vE6U+4mm+zRoXz5nSmFiIotpPCJD8rL8/pUJvLV7\n" +
                "mQRnPlDk1FdvPk+afvVTtbb7G7TI4Kv1WqsI0DFayajLqJfpEFUVJJqFlHaLNa3CSSN29KzZ2tki\n" +
                "nuGeX7uCo7VShigtTE0K7xjdkila4FpxqMMQneWIfNk5q09wsdsiRzFXbBz60zUZVumUP8u6P5QP\n" +
                "as9bRrzkLnZ79KNwLzXF2xBVkJH96rEimR0Y5BTqBURt4be0Nw0rtlec0aZq1reP5BgZW9fWgRaj\n" +
                "WRSAd31Iqrd30cQZn+cAY+X1qpqCawmpOhuCICcDb2q0lja28BsFn3y7t0hPvTtoI52xVreSfUk0\n" +
                "5NjPyzDOM+lVWeK5Xzk02zTzic+XHjNaWsm509DpaX7mPrtxjrVCFkWxjYKEeMHgDqarzEZdhpSx\n" +
                "G7nhvbuLbjYA/wAoptzPfwFZpJmuEX7/AJnp9a04nU2nkqpDNzmlityESK5+YHg+4p3BGppOn/ab\n" +
                "J5g4dMKwINVdb1dXdooflQDp60yaK30ODy9IuJYPOB8yMfd+tcnqrXH2faLj5ScGs07lIj1q9hjt\n" +
                "zNLuOz7oFcrommSeLdeihuZXitNwMzjtSahe3V/eR6daDflgv49K9K0Dw4NF0qPZtLv88rjvV9CZ\n" +
                "HSXHhOC10xLuGe1gtQu1FauUuYgg83TInWVeTt43VoR5u8XV8d6KNwVulWNN0uHV9W+zNeCygjhM\n" +
                "xkHtSS5NxGJaXVzqD2snn78yfvUz0xW9cSQDiFcCud1GwtvD96NRtTu87Pyc/PTY9YgnlAmZoMjj\n" +
                "NPfVDNqW/kgjeMOPnGMU1bhICqMcEjFZZaLzC32pZR04qaI+ZkmT5kPHFCYWJfInWLf50RUGopry\n" +
                "Oy8tlJlcEk4qK+cvbG3d/vH9Kzo5rWGMgvu2UwEF99rneQkhvRvStJTaECOWPqPSsqBR5nmNj5+m\n" +
                "Kkmum88FT04NAWP/2QAAAGkO";


        String filename = "D:\\11111.jpg";
        GenerateImage(base54str, filename);
        System.out.println("ok::"+filename);


    }


    public static boolean GenerateImage(String imgStr, String imgFilePath) {// 对字节数组字符串进行Base64解码并生成图片
        if(imgStr == null){// 图像数据为空
            return false;
        }
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // Base64解码
            byte[] bytes = decoder.decodeBuffer(imgStr);
            for (int i = 0; i < bytes.length; ++i) {
                if (bytes[i] < 0) {// 调整异常数据
                    bytes[i] += 256;
                }
            }
            // 生成jpeg图片
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(bytes);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
