package lotto.domain;

public class LottoMoney {
	public static final LottoMoney FIRST_PRIZE = new LottoMoney(2_000_000_000);
	public static final LottoMoney SECOND_PRIZE = new LottoMoney(30_000_000);
	public static final LottoMoney THIRD_PRIZE = new LottoMoney(1_500_000);
	public static final LottoMoney FOURTH_PRIZE = new LottoMoney(50_000);
	public static final LottoMoney FIFTH_PRIZE = new LottoMoney(5_000);
	public static final LottoMoney MISS_PRIZE = new LottoMoney(0);

	private static final long ZERO = 0;
	private static final long UNIT = 1000;
	private static final long MAX_BOUND = 100000;
	public static final long LOTTO_PRICE = 1000;

	private final long money;

	public LottoMoney(String money) {
		this.money = validate(money);
	}

	private LottoMoney(long money) {
		this.money = money;
	}

	private long validate(String money) {
		validateNullOrEmpty(money);
		long parsedMoney = parseToLong(money);
		validatePositive(parsedMoney);
		validateUnit(parsedMoney);
		validateMaxBound(parsedMoney);
		return parsedMoney;
	}

	private void validateNullOrEmpty(String money) {
		if (money == null || money.isEmpty()) {
			throw new InvalidLottoMoneyException(InvalidLottoMoneyException.NULL_OR_EMPTY);
		}
	}

	private long parseToLong(String money) {
		try {
			return Long.parseLong(money);
		} catch (NumberFormatException ne) {
			throw new InvalidLottoMoneyException(InvalidLottoMoneyException.NOT_INTEGER);
		}
	}

	private void validatePositive(long parsedMoney) {
		if (parsedMoney <= ZERO) {
			throw new InvalidLottoMoneyException(InvalidLottoMoneyException.NOT_POSITIVE);
		}
	}

	private void validateUnit(long parsedMoney) {
		if (parsedMoney % UNIT != ZERO) {
			throw new InvalidLottoMoneyException(InvalidLottoMoneyException.INVALID_UNIT);
		}
	}

	private void validateMaxBound(long parsedMoney) {
		if (parsedMoney > MAX_BOUND) {
			throw new InvalidLottoMoneyException(InvalidLottoMoneyException.OUT_OF_BOUND);
		}
	}

	public int getNumberOfLotto() {
		return (int)(money / LOTTO_PRICE);
	}

	public long getMoney() {
		return money;
	}
}
