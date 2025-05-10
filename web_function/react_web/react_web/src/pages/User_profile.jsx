import '../assets/style/user_profile/user_profile.css'
import Input_1 from '../component/input/Input_1'

export default function User_profile(){

    return<>
        <div className="main__profile">
            <div className="main__profile--content">
                <div className="content__avatar">
                    <div className="content__avatar--img">
                        <img src="/img/b23d923ac45f6bdac43c9c4c329bd7da.gif" alt="" />
                    </div>
                </div>
                <div className="content__item">
                    <div className="content__item--infor">
                        <div className="infor__item">
                            <Input_1 label="Name user" type="text" id="name_user_input" onChangeValue={(val) => {
                                    console.log(val)
                                }}/>
                        </div>
                        <div className="infor__item">
                            <Input_1 label="Age" type="text" id="name_user_input" onChangeValue={(val) => {
                                    console.log(val)
                                }}/>
                        </div>
                        <div className="infor__item">
                            <Input_1 label="Time of birth" type="text" id="name_user_input" onChangeValue={(val) => {
                                    console.log(val)
                                }}/>
                        </div>
                        <div className="infor__item">
                            <Input_1 label="Phone number" type="text" id="name_user_input" onChangeValue={(val) => {
                                    console.log(val)
                                }}/>
                        </div>
                        <div className="infor__item">
                            <Input_1 label="Job" type="text" id="name_user_input" onChangeValue={(val) => {
                                    console.log(val)
                                }}/>
                        </div>
                        <div className="infor__item">
                            <div className="infor__item--button">
                                <p>Save</p>
                            </div>
                        </div>
                        <div className="infor__item">
                            <div className="infor__item--mail">
                                <div className="mail__content">
                                    <p>Email: thichthichich@gmail.com</p>
                                </div>
                                <div className="mail__btn">
                                    <p>Change</p>
                                </div>
                            </div>
                        </div>
                        <div className="infor__item">
                            <div className="infor__item--address">
                                <div className="address__main">
                                    <div className="address__main--header">
                                        <p>Address</p>
                                    </div>
                                    <div className="address__main--item">
                                        <div className="item__content">
                                            <p>address 1</p>
                                            <p>delete</p>
                                        </div>
                                        <div className="item__content">
                                            <p>address 1</p>
                                            <p>delete</p>
                                        </div>
                                        <div className="item__content">
                                            <p>address 1</p>
                                            <p>delete</p>
                                        </div>
                                        <div className="item__content">
                                            <p>address 1</p>
                                            <p>delete</p>
                                        </div>
                                        <div className="item__content">
                                            <p>address 1</p>
                                            <p>delete</p>
                                        </div>
                                        <div className="item__content">
                                            <p>address 1</p>
                                            <p>delete</p>
                                        </div>
                                        <div className="item__content">
                                            <p>address 1</p>
                                            <p>delete</p>
                                        </div>
                                        <div className="item__content">
                                            <p>address 1</p>
                                            <p>delete</p>
                                        </div>
                                        <div className="item__content">
                                            <p>address 1</p>
                                            <p>delete</p>
                                        </div>
                                        <div className="item__content">
                                            <p>address 1</p>
                                            <p>delete</p>
                                        </div>
                                        <div className="item__content">
                                            <p>address 1</p>
                                            <p>delete</p>
                                        </div>
                                        <div className="item__content">
                                            <p>address 1</p>
                                            <p>delete</p>
                                        </div>
                                        <div className="item__content">
                                            <p>address 1</p>
                                            <p>delete</p>
                                        </div>
                                        <div className="item__content">
                                            <p>address 1</p>
                                            <p>delete</p>
                                        </div>
                                        <div className="item__content">
                                            <p>address 1</p>
                                            <p>delete</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </>
}