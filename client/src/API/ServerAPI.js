import axios from "axios";

const host = "http://prepro31.iptime.org:8080";

export async function userProfileData(id) {
  const member = await axios.get(`${host}/members/${id}`);
  const profile = await axios.get(`${host}/profiles/${id}`);
  const questionsList = await axios.get(`${host}/questions`);

  const 내질문 = questionsList.data.data.filter(data => data.memberName === member.data.data.memberName)

  return {
    name: member.data.data.memberName,
    profileTitle: profile.data.data.profileTitle,
    homepage: profile.data.data.homepage,
    location: profile.data.data.location,
    profileRegDate: profile.data.data.profileRegDate,
    profileView: profile.data.data.profileView,
    about: profile.data.data.about,
    myquestions: 내질문
  };
}
