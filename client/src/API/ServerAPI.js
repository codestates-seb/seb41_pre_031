import axios from "axios";

const host = "http://prepro31.iptime.org:8080";

export async function userProfileData(id) {
  const member = await axios.get(`${host}/members/${id}`);
  const profile = await axios.get(`${host}/profiles/${id}`);
  const questionsList = await axios.get(`${host}/questions`);
  const answersList = await axios.get(`${host}/answers`);
  const 내답변 = answersList.data.data.filter(
    (data) => data.memberName === member.data.data.memberName
  );
  const 내질문 = questionsList.data.data.filter(
    (data) => data.memberName === member.data.data.memberName
  );
  const 내답변필터 = 내답변.map(data => {
    const 타이틀 = questionsList.data.data.filter(data2 => {
        return data2.questionId === data.questionId
    })
    return{
        answerId: data.answerId,
        RegDate: data.answerRegDate,
        questionId: data.questionId,
        title: 타이틀[0].questionTitle
    }
  })
  const 내질문필터 = 내질문.map(data => {
    const 타이틀 = questionsList.data.data.filter(data2 => {
        return data2.questionId === data.questionId
    })
    return{
        RegDate: data.questionRegDate,
        questionId: data.questionId,
        title: 타이틀[0].questionTitle
    }
  })
  return {
    name: member.data.data.memberName,
    profileTitle: profile.data.data.profileTitle,
    homepage: profile.data.data.homepage,
    location: profile.data.data.location,
    profileRegDate: profile.data.data.profileRegDate,
    profileView: profile.data.data.profileView,
    about: profile.data.data.about,
    myquestions: 내질문필터,
    myanswers: 내답변필터,
    point: profile.data.data.point,
  };
}

export async function questionData(id) {
  const question = await axios.get(`${host}/questions/${id}`);
  const users = await axios.get(`${host}/members`);
  const user = users.data.data.filter(
    (data) => data.memberName === question.data.data.memberName
  );
  const profile = await axios.get(`${host}/profiles/${user[0].memberId}`);

  const 지금 = new Date();
  const 작성날짜 = new Date(question.data.data.questionRegDate);
  const 수정날짜 = new Date(question.data.data.questionLastDate);
  const diffDate1 = 지금.getTime() - 작성날짜.getTime();
  const diffDate2 = 지금.getTime() - 수정날짜.getTime();
  const startDay = Math.abs(diffDate1 / (1000 * 60 * 60 * 24));
  const lastDay = Math.abs(diffDate2 / (1000 * 60 * 60 * 24));
  const 첫날짜 = startDay < 1 ? "today" : Math.ceil(startDay) + " ago";
  const 마지막날짜 = lastDay < 1 ? "today" : Math.ceil(lastDay) + " ago";

  return {
    answersId:undefined,
    questionTitle: question.data.data.questionTitle,
    questionRegDate: 첫날짜,
    questionLastDate: 마지막날짜,
    questionView: question.data.data.questionView,
    questionLikes: question.data.data.questionLikes,
    questionBody: question.data.data.questionBody,
    user: user,
    userprofile: profile.data.data,
  };
}

export async function answerData(id) {
  const answers = await axios.get(`${host}/answers`);
  const users = await axios.get(`${host}/members`);
  const filteranswers = answers.data.data.filter(
    (data) => String(data.questionId) === id
  );
  const profile = await axios.get(`${host}/profiles`);
  const 유저프로필 = filteranswers.map((data) => {
    let 유저필터 = users.data.data.filter((data2) => {
      return data2.memberName === data.memberName;
    });
    return profile.data.data[유저필터[0].memberId];
  });

  const 정리한데이터 = filteranswers.map((data, index) => {
    let 유저이름 = users.data.data.filter((data2) => {
      return data2.memberName === data.memberName;
    });

    const 지금 = new Date();
    const 작성날짜 = new Date(data.answerRegDate);
    const 수정날짜 = new Date(data.answerLastDate);
    const diffDate1 = 지금.getTime() - 작성날짜.getTime();
    const diffDate2 = 지금.getTime() - 수정날짜.getTime();
    const startDay = Math.abs(diffDate1 / (1000 * 60 * 60 * 24));
    const lastDay = Math.abs(diffDate2 / (1000 * 60 * 60 * 24));
    const 첫날짜 = startDay < 1 ? "today" : Math.ceil(startDay) + " ago";
    const 마지막날짜 = lastDay < 1 ? "today" : Math.ceil(lastDay) + " ago";

    return {
      answersId: data.answerId,
      answersRegDate: 첫날짜,
      answersLastDate: 마지막날짜,
      answersLikes: data.answerLikes,
      answersBody: data.answerBody,
      user: 유저이름,
      userprofile: 유저프로필[index],
      answersLength: 유저프로필.length,
    };
  });
  return 정리한데이터;
}

export async function userQuComment(id) {
  const comments = await axios.get(`${host}/comments`);
  const filterCommets = comments.data.data.filter((data) => {
    return String(data.questionId) === id;
  });

  const users = await axios.get(`${host}/members`);

  const 질문댓글 = filterCommets.map((data) => {
    let userId = users.data.data.filter((data2) => {
      return data2.memberName === data.memberName;
    });

    const 지금 = new Date();
    const 작성날짜 = new Date(data.commentRegDate);
    const diffDate = 지금.getTime() - 작성날짜.getTime();
    const startDay = Math.abs(diffDate / (1000 * 60 * 60 * 24));
    const day = startDay < 1 ? "today" : Math.ceil(startDay) + " ago";

    return {
      body: data.commentBody,
      name: data.memberName,
      date: day,
      userId: userId[0].memberId,
    };
  });

  return 질문댓글;
}

export async function userAnComment(id) {
  //답변 ID
  const comments = await axios.get(`${host}/comments`);
  const filterCommets = comments.data.data.filter((data) => {
    return Number(data.answerId) === id;
  });

  const users = await axios.get(`${host}/members`);
  const 답변댓글 = filterCommets.map((data) => {
    let userId = users.data.data.filter((data2) => {
      return data2.memberName === data.memberName;
    });

    const 지금 = new Date();
    const 작성날짜 = new Date(data.commentRegDate);
    const diffDate = 지금.getTime() - 작성날짜.getTime();
    const startDay = Math.abs(diffDate / (1000 * 60 * 60 * 24));
    const day = startDay < 1 ? "today" : Math.ceil(startDay) + " ago";

    return {
      body: data.commentBody,
      name: data.memberName,
      date: day,
      userId: userId[0].memberId,
    };
  });

  return 답변댓글;
}
