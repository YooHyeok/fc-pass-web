package com.fc.pass.service.statistics;

import com.fc.pass.repository.statistics.StatisticsRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StatisticsServiceTest {
    @Mock
    private StatisticsRepository statisticsRepository;

    @InjectMocks // Mock(Repository)를 주입받은 InjectMock
    private StatisticsService statisticsService;

    // Junit5에서만 지원하는 기능
    @Nested // 계층적인 구조로 테스트를 만들 수 있는 애노테이션
    @DisplayName("통계 데이터를 기반으로 차트만들기")
    class MakeChartData {
        final LocalDateTime to = LocalDateTime.of(2024, 4, 5, 0, 0);
        @DisplayName("통계 데이터가 있을 때")
        @Test
        void makeChartData_when_hasStatistics() {
            //[given]
            List<AggregatedStatistics> statisticsList = List.of(
                    new AggregatedStatistics(to.minusDays(1), 15, 10, 5),
                    new AggregatedStatistics(to, 10, 8, 2)
            );

            //[when] - 수행할 동작
            when(statisticsRepository.findByStatisticsAtBetweenAndGroupBy(eq(to.minusDays(10)), eq(to))) //repository를 통해 조회 쿼리를 호출했을경우
                    .thenReturn(statisticsList); // 지정한 매개값인 statisticsList 객체 통계 데이터가 반환될 것이다.
            final ChartData chartData = statisticsService.makeChartData(to);

            //[then] - 수행 결과
            // 1. repository 호출 여부
            verify(statisticsRepository, times(1)).findByStatisticsAtBetweenAndGroupBy(eq(to.minusDays(10)), eq(to));
            // 2. 결과값인 chartData는 null일 수 없음.
            assertNotNull(chartData);
            // 3. 결과값 내부 데이터 비교
            assertEquals(new ArrayList<>(List.of("04-04", "04-05")), chartData.getLabels());
            assertEquals(new ArrayList<>(List.of(10L, 8L)), chartData.getAttendedCounts());
            assertEquals(new ArrayList<>(List.of(5L, 2L)), chartData.getCancelledCounts());
        }
        @Test
        @DisplayName("통계 데이터가 없을 때")
        void makeChartData_when_notHasStatistics() {
            //[when] - 수행할 동작
            when(statisticsRepository.findByStatisticsAtBetweenAndGroupBy(eq(to.minusDays(10)), eq(to))) //repository를 통해 조회 쿼리를 호출했을경우
                    .thenReturn(Collections.emptyList()); // 값이 없는 통계 데이터가 반환될 것이다.
            final ChartData chartData = statisticsService.makeChartData(to);

            //[then] - 수행 결과
            // 1. repository 호출 여부
            verify(statisticsRepository, times(1)).findByStatisticsAtBetweenAndGroupBy(eq(to.minusDays(10)), eq(to));
            // 2. 결과값인 chartData는 null일 수 없음.
            assertNotNull(chartData);
            // 3. 결과값 내부 데이터 비교
            assertTrue(chartData.getLabels().isEmpty());
            assertTrue(chartData.getAttendedCounts().isEmpty());
            assertTrue(chartData.getCancelledCounts().isEmpty());

        }
    }

    @Test
    @DisplayName("차트 데이터 생성 테스트")
    void test_makeChartDate() {
        //[given]
        final LocalDateTime to = LocalDateTime.of(2024, 4, 5, 0, 0);

        List<AggregatedStatistics> statisticsList = List.of(
                new AggregatedStatistics(to.minusDays(1), 15, 10, 5),
                new AggregatedStatistics(to, 10, 8, 2)
        );

        //[when] - 수행할 동작

        /**
         * when을 통해 Repository의 메소드에 값을 넘기고, thenReturn을 통해 반환값을 예상한다.
         * 이때 eq를 통해 매개변수의 값의 동일성을 확인한다.
         * 동일성에 대한 기준은 이후 injectMock을 통해 mocking된 repository를 주입받은 service로부터 주입된 값이
         * 실제 프로세스상 repository로 넘겨 받게 되는경우가 많고, 이때 넘겨지는값과 when에서 주입한 값이 실제로 일치하는지에 대해 확인한다.
         */
        when(statisticsRepository.findByStatisticsAtBetweenAndGroupBy(eq(to.minusDays(10)), eq(to))) //repository를 통해 조회 쿼리를 호출했을경우
                .thenReturn(statisticsList); // 지정한 매개값인 statisticsList 객체 통계 데이터가 반환될 것이다.
        final ChartData chartData = statisticsService.makeChartData(to);

        //[then] - 수행 결과
        // 1. repository 호출 여부
        verify(statisticsRepository, times(1)).findByStatisticsAtBetweenAndGroupBy(eq(to.minusDays(10)), eq(to));
        // 2. 결과값인 chartData는 null일 수 없음.
        assertNotNull(chartData);
        // 3. 결과값 내부 데이터 비교
        assertEquals(new ArrayList<>(List.of("04-04", "04-05")), chartData.getLabels());
        assertEquals(new ArrayList<>(List.of(10L, 8L)), chartData.getAttendedCounts());
        assertEquals(new ArrayList<>(List.of(5L, 2L)), chartData.getCancelledCounts());

    }
}