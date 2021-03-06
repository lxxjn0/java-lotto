package lotto.domain.lottoNumber;

import static java.util.stream.Collectors.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoNumberCache {

	public static final int MIN_LOTTO_NUMBER = 1;
	public static final int MAX_LOTTO_NUMBER = 45;
	private static final Map<Integer, LottoNumber> CACHE = new HashMap<>();

	static {
		for (int lottoNumber = MIN_LOTTO_NUMBER; lottoNumber <= MAX_LOTTO_NUMBER; lottoNumber++) {
			CACHE.put(lottoNumber, new LottoNumber(lottoNumber));
		}
	}

	public static LottoNumber asLottoNumber(int number) {
		if (number >= MIN_LOTTO_NUMBER && number <= MAX_LOTTO_NUMBER) {
			return CACHE.get(number);
		}
		return new LottoNumber(number);
	}

	public static List<LottoNumber> asLottoNumber(int... numbers) {
		return Arrays.stream(numbers)
			.mapToObj(LottoNumberCache::asLottoNumber)
			.collect(toList());
	}

	public static Collection<LottoNumber> values() {
		return Collections.unmodifiableCollection(CACHE.values());
	}

}
