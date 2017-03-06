package net.abc101.hangul2alphabet;

public class hangul2alphabet {

	/**
	 * 한글 초성 유니코드 리스트(19) First sound Korean character unicode
	 * 
	 * ㄱ, ㄲ, ㄴ, ㄷ, ㄸ, ㄹ, ㅁ, ㅂ, ㅃ, ㅅ, ㅆ, ㅇ, ㅈ, ㅉ, ㅊ, ㅋ, ㅌ, ㅍ, ㅎ
	 */
	public static char[] arrayChosung = { 0x3131, 0x3132, 0x3134, 0x3137, 0x3138, 0x3139, 0x3141, 0x3142, 0x3143,
			0x3145, 0x3146, 0x3147, 0x3148, 0x3149, 0x314a, 0x314b, 0x314c, 0x314d, 0x314e };

	/**
	 * 한글 중성 유니코드 리스트(21) Middle sound Korean character unicode
	 * 
	 * ㅏ, ㅐ, ㅑ, ㅒ, ㅓ, ㅔ, ㅕ, ㅖ, ㅗ, ㅠ, ㅘ, ㅛ, ㅙ, ㅚ, ㅜ, ㅝ, ㅞ, ㅟ, ㅡ, ㅢ, ㅣ
	 */
	public static char[] arrayJungsung = { 0x314f, 0x3150, 0x3151, 0x3152, 0x3153, 0x3154, 0x3155, 0x3156, 0x3157,
			0x3158, 0x3159, 0x315a, 0x315b, 0x315c, 0x315d, 0x315e, 0x315f, 0x3160, 0x3161, 0x3162, 0x3163 };

	/**
	 * 한글 종성 유니코드 리스트(28) Last sound Korean character unicode
	 * 
	 * Null, ㄱ, ㄲ, ㄳ, ㄴ, ㄵ, ㄶ, ㄷ, ㄹ, ㄺ, ㄻ, ㄼ, ㄽ, ㄾ, ㄿ, ㅀ, ㅁ, ㅂ, ㅄ, ㅅ, ㅆ, ㅇ, ㅈ, ㅊ, ㅋ, ㅌ, ㅍ, ㅎ,
	 */

	public static char[] arrayJongsung = { 0x0000, 0x3131, 0x3132, 0x3133, 0x3134, 0x3135, 0x3136, 0x3137, 0x3139,
			0x313a, 0x313b, 0x313c, 0x313d, 0x313e, 0x313f, 0x3140, 0x3141, 0x3142, 0x3144, 0x3145, 0x3146, 0x3147,
			0x3148, 0x314a, 0x314b, 0x314c, 0x314d, 0x314e };

	/**
	 * 알파벳 매칭
	 */

	public static String[] arrayJaumAlphabet = { "r", "R", "rt", "s", "sw", "sg", "e", "E", "f", "fr", "fa", "fq", "ft",
			"fx", "fv", "fg", "a", "q", "Q", "qt", "t", "T", "d", "w", "W", "c", "z", "x", "v", "g" };

	public static String[] arrayMoumAlphabet = { "k", "o", "i", "O", "j", "p", "u", "P", "h", "hk", "ho", "hl", "y",
			"n", "nj", "np", "nl", "b", "m", "ml", "l" };

	/**
	 * 자소 분리
	 * 
	 * @param hangul
	 * @return
	 */
	public static String separate(String hangul) {

		String jaso = "";

		for (int i = 0; i < hangul.length(); i++) {
			char chars = (char) (hangul.charAt(i) - 0xAC00);

			/* 한글 일 경우와 아닐 경우 (Hangul or not) */
			if (chars >= 0 && chars <= 11172) {

				/* 자모 분리 (Separate consonant and vowel) */
				int cho = chars / (21 * 28);
				int jung = chars % (21 * 28) / 28;
				int jong = chars % (21 * 28) % 28;

				jaso = jaso + arrayChosung[cho] + arrayJungsung[jung];

				/* 종성있으면 추가 (Last sound only if exist) */
				if (jong != 0x0000) {
					jaso = jaso + arrayJongsung[jong];
				}

			} else {
				jaso = jaso + ((char) (chars + 0xAC00));
			}
		}
		return jaso;
	}

	/**
	 * 자모 알파벳 매칭
	 * 
	 * @param jaso
	 * @return
	 */

	public static String toAlphabet(String jaso) {
		String alph = "";

		for (int i = 0; i < jaso.length(); i++) {
			char jamo = (char) (jaso.charAt(i) - 0xAC00);

			if (jamo >= 34097 && jamo <= 34126) {
				int jaum = (jamo - 34097);
				alph = alph + arrayJaumAlphabet[jaum];
			} else if (jamo >= 34127 && jamo <= 34147) {
				int moum = (jamo - 34127);
				alph = alph + arrayMoumAlphabet[moum];
			} else {
				alph = alph + ((char) (jamo + 0xAC00));
			}
		}
		return alph;
	}

	/**
	 * 메인
	 * 
	 * @param args
	 */

	public static void main(String args[]) {

		/* sample */
		String jaso = separate("123 한글 abc");
		String alph = toAlphabet(jaso);

		System.out.println(jaso);
		System.out.println(alph);
	}

}
