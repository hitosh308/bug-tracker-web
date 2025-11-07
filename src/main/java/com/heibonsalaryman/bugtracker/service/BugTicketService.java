package com.heibonsalaryman.bugtracker.service;

import com.heibonsalaryman.bugtracker.model.BugStatus;
import com.heibonsalaryman.bugtracker.model.BugTicket;
import com.heibonsalaryman.bugtracker.model.BugTicketRegistration;
import com.heibonsalaryman.bugtracker.model.BugTrendSummary;
import com.heibonsalaryman.bugtracker.model.MonthlyBugCount;
import com.heibonsalaryman.bugtracker.repository.BugTicketRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Service
public class BugTicketService {

    private final BugTicketRepository repository;

    public BugTicketService(BugTicketRepository repository) {
        this.repository = repository;
    }

    public List<BugTicket> findAll() {
        return repository.findAll();
    }

    @Transactional
    public long register(BugTicketRegistration registration) {
        return repository.insert(registration);
    }

    public BugTrendSummary getMonthlyTrend(int months) {
        if (months < 1) {
            throw new IllegalArgumentException("months must be greater than 0");
        }

        YearMonth currentMonth = YearMonth.now();
        YearMonth startMonth = currentMonth.minusMonths(months - 1L);
        LocalDate startDate = startMonth.atDay(1);

        List<MonthlyBugCount> rawCounts = repository.findMonthlyCountsFrom(startDate);

        List<YearMonth> monthSeries = new ArrayList<>();
        for (int i = 0; i < months; i++) {
            monthSeries.add(startMonth.plusMonths(i));
        }

        Map<BugStatus, List<Long>> statusCounts = new EnumMap<>(BugStatus.class);
        for (BugStatus status : BugStatus.values()) {
            List<Long> counts = new ArrayList<>(Collections.nCopies(months, 0L));
            statusCounts.put(status, counts);
        }

        for (MonthlyBugCount count : rawCounts) {
            int index = (int) ChronoUnit.MONTHS.between(startMonth, count.month());
            if (index >= 0 && index < months) {
                statusCounts.get(count.status()).set(index, count.count());
            }
        }

        return new BugTrendSummary(monthSeries, statusCounts);
    }
}
