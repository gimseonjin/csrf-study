package com.kerry.miniiam

import com.kerry.miniiam.repository.UserRepository
import com.kerry.miniiam.service.DocumentService
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Component

@SpringBootApplication
class MiniIamApplication

fun main(args: Array<String>) {
    runApplication<MiniIamApplication>(*args)
}

//
//@Component
//class SampleDocLoader(
//    private val documentService: DocumentService,
//    private val userRepository: UserRepository
//) : CommandLineRunner {
//
//    override fun run(vararg args: String?) {
//        val user = userRepository.findByUsername("kerry")
//            ?: return println("user1 계정이 없어 문서 생성 생략됨")
//
//        if (documentService.getMyDocuments(user).isNotEmpty()) {
//            println("샘플 문서 이미 존재함")
//            return
//        }
//
//        val sampleDocs = listOf(
//            "23Q4 전략사업부 OKR 회의록" to """
//        2023년 4분기 전략사업부 전체 OKR 정리본.
//        핵심 추진 과제는 B2B SaaS 진출, 금융 API 강화, 북미 파트너사 확대입니다.
//        외부 유출 시 전략 노출 위험이 있어 보안 등급은 내부 최고 수준입니다.
//    """.trimIndent(),
//
//            "신규 금융 서비스 런칭 일정 공유" to """
//        2024년 2월 'MiniPay' 출시 관련 타임라인 정리.
//        주요 기능: 간편결제, 정기구독 청구, 인증 연동.
//        디자인 확정은 1월 10일, 개발 완료 마감은 1월 25일.
//    """.trimIndent(),
//
//            "B사와의 전략 제휴 제안서 (CONFIDENTIAL)" to """
//        B사에게 제안한 공동 브랜드/기술 제휴안.
//        예상 MOU 일정: 2024.03.01 ~ 2024.04.01.
//        내부 공유용이며, 유출 시 신뢰도 손상 우려 있음.
//    """.trimIndent(),
//
//            "사내 인사 전환 대상자 정리" to """
//        R&D 조직 내 보직 전환 예정자 리스트 (2차안).
//        CTO 승인 대기 중, 최종본은 HR 시스템 반영 예정.
//        열람 권한은 팀장 이상으로 제한.
//    """.trimIndent(),
//
//            "클라우드 이전 프로젝트 보안 리뷰" to """
//        현재 자사 서비스의 GCP → AWS 마이그레이션 계획에 대한 보안 리스크 분석.
//        S3 권한 정책, IAM 역할, 내부 접근 제어에 대한 우려 제기됨.
//    """.trimIndent(),
//
//            "개발 조직 구조 개편안 v3" to """
//        BE/FE 구분 해체 후 팀 단위 편성안.
//        Squad 기반 도입 및 CP/Tech Lead 역할 구체화.
//        CEO 피드백 반영 전까지는 비공개.
//    """.trimIndent(),
//
//            "고객 데이터 백업 정책 개정 초안" to """
//        개인정보 보존 기간 3년 → 1년 단축안 포함.
//        법무팀 자문 검토 중, ISO 감사 전 제출 예정.
//        외부 노출 시 법적 리스크 발생 가능.
//    """.trimIndent(),
//
//            "사내 협업툴 도입 비교표 (Notion vs Confluence)" to """
//        각 부서 요구사항 기반 협업툴 비교 분석.
//        가격, 연동성, 모바일 UX, SSO 지원 등 비교.
//        경영진 회의에 사용된 문서.
//    """.trimIndent(),
//
//            "23년 사업 손익 정리본 (내부용)" to """
//        23년 연간 손익 및 투자 회수 내역 정리.
//        미공개 적자 구간 포함되어 있으므로 대외비 취급.
//    """.trimIndent(),
//
//            "모빌리티 사업본부 해커톤 심사 결과표" to """
//        2023 사내 해커톤 최종 심사 점수 및 내부 코멘트 포함.
//        수상작 코드에 민감한 고객사 연동 정보가 포함되어 있음.
//        배포 금지 문서.
//    """.trimIndent()
//        )
//
//        sampleDocs.forEach { (title, content) ->
//            documentService.createDocument(title, content, user)
//        }
//
//        println("✅ PM용 고위험 문서 10건 생성 완료 (CSRF 시나리오용)")
//    }
//}