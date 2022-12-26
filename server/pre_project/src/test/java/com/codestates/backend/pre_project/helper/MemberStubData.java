package com.codestates.backend.pre_project.helper;

import com.codestates.backend.pre_project.member.dto.MemberDto;
import com.codestates.backend.pre_project.member.entity.Member;
import com.codestates.backend.pre_project.point.entity.Point;
import com.codestates.backend.pre_project.response.SingleResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MemberStubData {
    private static Map<HttpMethod, Object> stubRequestBody;
    static {
        stubRequestBody = new HashMap<>();
        stubRequestBody.put(HttpMethod.POST, new MemberDto.Post("vdcf2000@gmail.com",
                "asdf123","강신찬"));
        stubRequestBody.put(HttpMethod.PATCH,
                new MemberDto.Patch(1, "asdf123", "김현성"));
    }

    public static class MockMember {
        public static Object getRequestBody(HttpMethod method) {
            return stubRequestBody.get(method);
        }

        public static MemberDto.Response getSingleResponseBody() {
            return new MemberDto.Response(1L,
                    "vdcf2000@gmail.com",
                    "강신찬",
                    "asdf123"
                    );
        }

        public static MemberDto.Response getSingleResponseBody(long memberId, String email, String MemberName,String password ) {
            long optionalMemberId = Optional.ofNullable(memberId).orElse(1L);
            String optionalEmail = Optional.ofNullable(email).orElse("vdcf2000@gmail.com");
            String optionalName = Optional.ofNullable(MemberName).orElse("강신찬");
            String optionalPW = Optional.ofNullable(password).orElse("asdf123");
            return new MemberDto.Response(optionalMemberId,
                    optionalEmail,
                    optionalName,
                    optionalPW);
        }

        public static Member getSingleResultMember(long memberId) {
            Member member = new Member(1L, "vdcf2000@gmail.com","asdf123","강신찬");
            member.setMemberId(memberId);
            //member.setMemberStatus(Member.MemberStatus.MEMBER_ACTIVE);
//            member.setStamp(new Stamp());
            return member;
        }

        public static Member getSingleResultMember(long memberId, Map<String, String> updatedInfo) {
            String memberName = Optional.ofNullable(updatedInfo.get("memberName")).orElse("강신찬");
            String password = Optional.ofNullable(updatedInfo.get("password")).orElse("asdf123");
            Member member = new Member(1l,"hgd@gmail.com", memberName, password);
            member.setMemberId(memberId);
//            member.setMemberStatus(Member.MemberStatus.MEMBER_ACTIVE);
//            member.setStamp(new Stamp());
            return member;
        }

        public static Page<Member> getMultiResultMember() {
            Member member1 = new Member(1l,"vdcf2000@gmail.com","asdf123","강신찬");
            member1.setMemberId(1L);  // 추가
//            member1.setMemberStatus(Member.MemberStatus.MEMBER_ACTIVE);
            member1.setPoint(new Point());

            Member member2 = new Member(2l,"vdcf2002@gmail.com","asdf123","김찬빈");
            member2.setMemberId(2L);  // 추가
//            member2.setMemberStatus(Member.MemberStatus.MEMBER_ACTIVE);
            member2.setPoint(new Point());

            return new PageImpl<>(List.of(member1, member2),
                    PageRequest.of(0, 10, Sort.by("memberId").descending()), // 정렬 지원 안됨. DB에서 정렬하지 않으므로..
                    2);
        }

        public static List<MemberDto.Response> getMultiResponseBody() {
            return List.of(
                    new MemberDto.Response(1L,
                            "vdcf2000@gmail.com",
                            "강신찬",
                            "asdf123"),
                    new MemberDto.Response(2L,
                            "vdcf2002@gmail.com",
                            "김찬빈",
                            "asdf123"));
        }
    }

//    public static class MockOrder {
//        public static Order getSingleResponseBody(long orderId) {
//            Order order = new Order();
//            order.setOrderId(orderId);
//            order.setOrderStatus(Order.OrderStatus.ORDER_CONFIRM);
//
//            return order;
//        }
//    }
}
