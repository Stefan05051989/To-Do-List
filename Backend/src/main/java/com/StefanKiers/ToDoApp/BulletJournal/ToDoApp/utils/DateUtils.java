package com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.utils;

import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.enums.MigrationTarget;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

/**
 * DateUtils
 * Challenge: com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.utils
 *
 * @author Stefan Kiers
 * @since 5-8-2026
 */
/* TODO: date class for to-do app.
 */
public class DateUtils {
    public static LocalDate resolveMigrationDate(MigrationTarget target) {
        LocalDate today = LocalDate.now();

        return switch (target){
            case TOMMOROW -> today.plusDays(1);
            case NEXT_MONDAY -> today.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
            case NEXT_MONTH_START -> today.plusMonths(1).withDayOfMonth(1);
            case NEXT_JANUARY_FIRST -> today.with(TemporalAdjusters.firstDayOfNextYear());
        };
    }
    public static boolean isDueOrPast(LocalDate targetDate) {
        return targetDate != null && !targetDate.isAfter(LocalDate.now());
    }

}
