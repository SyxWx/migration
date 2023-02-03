create table if not exists history_data
(
    customer_id bigint  not null,
    signal_no   varchar not null,
    event_time  bigint  not null,
    val         varchar,
    constraint pk primary key (customer_id, signal_no, event_time)
) column_encoded_bytes=0;